package tezea.si.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.dto.SmallRequestSearchDTO;
import tezea.si.service.EntityCreationFromDTOService;
import tezea.si.service.EntityToDTOService;
import tezea.si.service.SmallRequestSearchService;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {

	@Autowired
	SmallRequestDAO dao;

	@Autowired
	SmallClientDAO clientDao;

	@Autowired
	UserTezeaDAO userDao;

	@Autowired
	EntityCreationFromDTOService creator;
	
	@Autowired
	EntityToDTOService toDTO;
	
	@Autowired
	SmallRequestSearchService searchService;

	@Operation(summary = "Search for requests")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The list of requests corresponding to your search"),
			@ApiResponse(responseCode = "400", description = "If the search body could not be parsed") })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SmallRequest>> getRequests(
			@RequestBody SmallRequestSearchDTO search) {
		Specification<SmallRequest> spec = searchService.convert(search);
		return ResponseEntity.ok(dao.findAll(spec));
	}

	@Operation(summary = "Get one request by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The request with this id"),
			@ApiResponse(responseCode = "404", description = "If there is no request with this id") })
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmallRequest> getRequests(@PathVariable Long id) {
		Optional<SmallRequest> request = dao.findById(id);
		if (request.isPresent()) {
			return ResponseEntity.ok(request.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Create a request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "The new request"),
			@ApiResponse(responseCode = "400", description = "If the input request body could not be parsed") })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SmallRequestDTO> createRequest(@RequestBody SmallRequest request) {
		SmallRequest entity = creator.convertToEntity(request);
		
		SmallRequestDTO response = toDTO.convertToDTO(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}