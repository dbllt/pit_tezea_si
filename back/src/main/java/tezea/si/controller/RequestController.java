package tezea.si.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.dto.RequestsSearchDTO;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {

	@Autowired
	SmallRequestDAO dao;

	@Operation(summary = "Search for requests")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The list of requests corresponding to your search"),
			@ApiResponse(responseCode = "400", description = "If the search body could not be parsed") })
	@RequestMapping(method = RequestMethod.GET)
	public List<SmallRequest> getRequests(@RequestBody RequestsSearchDTO search) {
		return dao.findAll();
	}
	
	@Operation(summary = "Create a request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The new request"),
			@ApiResponse(responseCode = "400", description = "If the input request body could not be parsed") })
	@RequestMapping(method = RequestMethod.POST)
	public SmallRequest createRequest(@RequestBody SmallRequest request) {
		return dao.save(request);
	}

}