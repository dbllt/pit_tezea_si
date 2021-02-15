package tezea.si.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.dao.SiteDAO;
import tezea.si.model.business.Site;

@RestController
public class SiteController {
	
	@Autowired
    SiteDAO siteDAO;
	
	@Operation(summary = "get sites")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get sites") })
    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Site>> getClients() {
	     Iterable<Site> userIt = siteDAO.findAll();
	     return ResponseEntity.ok(userIt);
    }
	
	
	@Operation(summary = "save site")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create site") })
    @RequestMapping(value = "/createsite", method = RequestMethod.POST)
    public void saveEntreprise(@RequestBody Site s) {
		siteDAO.save(s);
	     
    }
}
