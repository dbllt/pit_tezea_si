package tezea.si.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import tezea.si.model.business.Client;
import tezea.si.utils.search.ClientSpecification;
import tezea.si.utils.search.SearchCriteria;

@Service
public class ClientSpecificationBuilder {
	private final List<SearchCriteria> params = new ArrayList<SearchCriteria>();

	public ClientSpecificationBuilder() {
	}

	public ClientSpecificationBuilder with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public Specification<Client> build() {
		if (params.size() == 0) {
			return null;
		}

		List<Specification<Client>> specs = params.stream().map(ClientSpecification::new)
				.collect(Collectors.toList());

		Specification<Client> result = specs.get(0);

		for (int i = 1; i < params.size(); i++) {
			result = Specification.where(result).and(specs.get(i));
		}
		return result;
	}
}
