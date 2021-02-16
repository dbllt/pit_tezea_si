package tezea.si.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import tezea.si.model.business.Client;
import tezea.si.model.dto.ClientSearchDTO;

public class ClientSearchService {
	
	@Autowired
	ClientSpecificationBuilder builder;
	
	public Specification<Client> convert(ClientSearchDTO client) {
		return builder.with("ville", ":", client.getAddress()).build();
	}

}
