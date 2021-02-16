package tezea.si.utils.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tezea.si.model.business.Client;

public class ClientSpecification implements Specification<Client> {
	private SearchCriteria criteria;

	public ClientSpecification(SearchCriteria searchCriteria) {
		this.criteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase("equals")) {
			return builder.equal(root.<String>get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("startswith")) {
			return builder.like(root.<String>get(criteria.getKey()),
					criteria.getValue() + "%");
		}
		else if (criteria.getOperation().equalsIgnoreCase("contains")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.<String>get(criteria.getKey()),
						"%" + criteria.getValue() + "%");
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}

}
