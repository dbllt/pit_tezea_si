package tezea.si.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.model.dto.RequestsSearchDTO;

@RestController
public class RequestController {

    @Operation(summary = "Search for requests")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The list of requests corresponding to your search"),
            @ApiResponse(responseCode = "400", description = "If the search body could not be parsed") })
	@RequestMapping(value = "/requests", method = RequestMethod.POST)
	public List<TemporaryRequestDTO> getRequests(@RequestBody RequestsSearchDTO search) {
		List<TemporaryRequestDTO> requests = new ArrayList<>();
		requests.add(new TemporaryRequestDTO("me", "today"));
		requests.add(new TemporaryRequestDTO("you", "tomorrow"));
		return requests;
	}

	private class TemporaryRequestDTO {
		private String client;
		private String date;

		public TemporaryRequestDTO() {
		}

		public TemporaryRequestDTO(String client, String date) {
			this.client = client;
			this.date = date;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

	}

}
