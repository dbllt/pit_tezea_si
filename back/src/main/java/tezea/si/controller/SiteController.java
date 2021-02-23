package tezea.si.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.model.business.Site;

@RestController
public class SiteController {
	
	@Operation(summary = "get sites")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get sites") })
    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Site>> getSites() {
	     return ResponseEntity.ok(Arrays.asList(Site.values()));
    }

}
