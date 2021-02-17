package tezea.si.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tezea.si.model.business.Client;
import tezea.si.model.dto.ClientSearchDTO;
import tezea.si.utils.search.SearchOperations;

@Component
public class ClientSearchService {
	
	@Autowired
	SpecificationBuilder<Client> builder;
	
	public Specification<Client> convert(ClientSearchDTO client) {
		return builder.with("codePostal", SearchOperations.CONTAINS, client.getAddress()).build();
	}

}
