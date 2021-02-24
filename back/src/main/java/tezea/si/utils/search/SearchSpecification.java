package tezea.si.utils.search;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tezea.si.model.business.Site;
import tezea.si.utils.errors.InvalidSearchTypeException;

public class SearchSpecification<T> implements Specification<T> {
	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	public SearchSpecification(SearchCriteria searchCriteria) {
		this.criteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {
		if (criteria.getJavaClass() == String.class) {
			switch (criteria.getOperation()) {
			case EQUALS:
				return builder.equal(constructKey(root, criteria),
						criteria.getValue().toString());
			case STARTSWITH:
				return builder.like(constructKey(root, criteria),
						criteria.getValue() + "%");
			case CONTAINS:
				return builder.like(constructKey(root, criteria),
						"%" + criteria.getValue() + "%");
			default:
				throw new InvalidSearchTypeException(
						"String search cannot use operation " + criteria.getOperation());
			}
		}
		if (criteria.getJavaClass() == Site.class) {
			switch (criteria.getOperation()) {
			case EQUALS:
				return builder.equal(constructKey(root, criteria), criteria.getValue());
			default:
				throw new InvalidSearchTypeException(
						"Site search cannot use operation " + criteria.getOperation());
			}
		}
		if (criteria.getJavaClass() == LocalDate.class) {
			switch (criteria.getOperation()) {
			case BEFORE:
				return builder.lessThanOrEqualTo(constructDateKey(root, criteria), (LocalDate) criteria.getValue());
			case AFTER:
				return builder.greaterThanOrEqualTo(constructDateKey(root, criteria), (LocalDate) criteria.getValue());
			default:
				throw new InvalidSearchTypeException(
						"Site search cannot use operation " + criteria.getOperation());
			}
		}
		throw new InvalidSearchTypeException(
				"Cannot search on type " + criteria.getJavaClass());
	}

	private Expression<String> constructKey(Root<T> root, SearchCriteria criteria) {
		if (criteria.hasNestedKey()) {
			return root.get(criteria.getKey()).<String>get(criteria.getNestedKey());
		}
		return root.<String>get(criteria.getKey());
	}
	
	private Expression<LocalDate> constructDateKey(Root<T> root, SearchCriteria criteria) {
		if (criteria.hasNestedKey()) {
			return root.get(criteria.getKey()).<LocalDate>get(criteria.getNestedKey());
		}
		return root.<LocalDate>get(criteria.getKey());
	}
}
