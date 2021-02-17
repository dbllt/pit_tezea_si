package tezea.si.utils.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tezea.si.utils.errors.InvalidSearchTypeException;

public class SearchSpecification<T> implements Specification<T> {
	private static final long serialVersionUID = 1L;
	private SearchCriteria<T> criteria;

	public SearchSpecification(SearchCriteria<T> searchCriteria) {
		this.criteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {
		if (criteria.getJavaClass() == String.class) {
			switch (criteria.getOperation()) {
			case EQUALS:
				return builder.equal(root.<String>get(criteria.getKey()),
						criteria.getValue().toString());
			case STARTSWITH:
				return builder.like(root.<String>get(criteria.getKey()),
						criteria.getValue() + "%");
			case CONTAINS:
				return builder.like(root.<String>get(criteria.getKey()),
						"%" + criteria.getValue() + "%");
			default:
				throw new InvalidSearchTypeException(
						"String search cannot use operation " + criteria.getOperation());
			}
		}
		throw new InvalidSearchTypeException(
				"Cannot search on type " + criteria.getJavaClass());
	}

}
