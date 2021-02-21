package tezea.si.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.business.request.SmallRequest_;
import tezea.si.model.dto.SmallRequestSearchDTO;
import tezea.si.utils.search.SearchOperations;

@Component
public class SmallRequestSearchService {

	@Autowired
	SpecificationBuilder<SmallRequest> builder;

	public Specification<SmallRequest> convert(SmallRequestSearchDTO smallRequest) {
		System.out.println("convert");
		if (smallRequest.getDescription() != null) {
			System.out.println("not null");
			builder.with(SmallRequest_.description, SearchOperations.CONTAINS,
					smallRequest.getDescription());
		}
		return builder.build();
	}

}
