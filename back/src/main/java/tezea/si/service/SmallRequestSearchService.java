package tezea.si.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tezea.si.model.business.SmallClient_;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.business.request.SmallRequest_;
import tezea.si.model.dto.SmallClientSearchDTO;
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
		if (smallRequest.getClient() != null) {
			SmallClientSearchDTO client = smallRequest.getClient();
			if (client.getLastName() != null) {
				builder.with(SmallRequest_.client, SmallClient_.lastName,
						SearchOperations.CONTAINS, client.getLastName());
			}
			if(client.getPhoneNumber() != null) {
				builder.with(SmallRequest_.client, SmallClient_.phoneNumber,
						SearchOperations.CONTAINS, client.getPhoneNumber());
			}
			if(client.getCity() != null) {
				builder.with(SmallRequest_.client, SmallClient_.city,
						SearchOperations.CONTAINS, client.getCity());
			}
		}
		if (smallRequest.getSite() != null) {
			builder.with(SmallRequest_.site, SearchOperations.EQUALS,
					smallRequest.getSite());
		}
		if (smallRequest.getStatus() != null) {
			builder.with(SmallRequest_.status, SearchOperations.EQUALS,
					smallRequest.getStatus());
		}
		if (smallRequest.getEndDate() != null && smallRequest.getStartDate() != null) {
			builder.with(SmallRequest_.date, SearchOperations.BEFORE,
					smallRequest.getEndDate());
		}
		if (smallRequest.getStartDate() != null) {
			builder.with(SmallRequest_.date, SearchOperations.AFTER,
					smallRequest.getStartDate());
		}
		if (smallRequest.getType() != null) {
			builder.with(SmallRequest_.type, SearchOperations.EQUALS,
					smallRequest.getType());
		}
		return builder.build();
	}

}
