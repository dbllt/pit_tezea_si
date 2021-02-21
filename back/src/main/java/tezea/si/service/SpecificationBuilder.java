package tezea.si.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tezea.si.utils.search.SearchCriteria;
import tezea.si.utils.search.SearchOperations;
import tezea.si.utils.search.SearchSpecification;

@Component
public class SpecificationBuilder<T> {
	private final List<SearchCriteria<T>> params = new ArrayList<SearchCriteria<T>>();

	public SpecificationBuilder() {
	}

	public SpecificationBuilder<T> with(SingularAttribute<T, ?> key,
			SearchOperations operation, Object value) {
		params.add(new SearchCriteria<T>(key, operation, value));
		return this;
	}

	public Specification<T> build() {
		if (params.size() == 0) {
			return null;
		}

		List<Specification<T>> specs = params.stream().map(SearchSpecification<T>::new)
				.collect(Collectors.toList());

		Specification<T> result = specs.get(0);

		for (int i = 1; i < params.size(); i++) {
			result = Specification.where(result).and(specs.get(i));
		}
		params.clear();
		return result;
	}
}
