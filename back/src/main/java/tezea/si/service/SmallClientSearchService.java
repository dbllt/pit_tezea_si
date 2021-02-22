package tezea.si.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tezea.si.model.business.SmallClient;
import tezea.si.model.business.SmallClient_;
import tezea.si.model.dto.SmallClientSearchDTO;
import tezea.si.utils.search.SearchOperations;

@Component
public class SmallClientSearchService {
	
	@Autowired
	SpecificationBuilder<SmallClient> builder;
	
	public Specification<SmallClient> convert(SmallClientSearchDTO smallClient) {
		return builder.with(SmallClient_.postCode, SearchOperations.CONTAINS, smallClient.getPostCode()).build();
	}

}
