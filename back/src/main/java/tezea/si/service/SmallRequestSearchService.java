package tezea.si.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tezea.si.model.business.SmallClient_;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.business.request.SmallRequest_;
import tezea.si.model.dto.SmallRequestSearchDTO;
import tezea.si.utils.search.SearchOperations;

@Component
public class SmallRequestSearchService {

	@Autowired
	SpecificationBuilder<SmallRequest> builder;

	public Specification<SmallRequest> convert(SmallRequestSearchDTO smallRequest) {
		if (smallRequest.getDescription() != null) {
			builder.with(SmallRequest_.description, SearchOperations.CONTAINS,
					smallRequest.getDescription());
		}
		if(smallRequest.getClient() != null) {
			builder.with(SmallRequest_.client, SmallClient_.lastName, SearchOperations.CONTAINS, smallRequest.getClient().getLastName());
		}
		return builder.build();
	}

}
