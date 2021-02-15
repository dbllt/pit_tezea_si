package tezea.si.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.minidev.json.JSONArray;
import tezea.si.dao.ClientDAO;
import tezea.si.model.business.Client;
import tezea.si.model.business.Entreprise;

@RestController
public class ClientController {
	
	@Autowired
    ClientDAO clientDAO;
	
	@Operation(summary = "get clients based on json filter")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get clients based on json filter") })
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public JSONArray getClients(@RequestParam String jsonfilter) {
		JSONArray json = new JSONArray();
	     Iterable<Client> clientsIt = clientDAO.findAll();
	     clientsIt.forEach((Client c ) ->{
	    	 json.add(c.toJSON());
	     });
	     return json;
    }
	
	
	@Operation(summary = "save client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get clients based on json filter") })
    @RequestMapping(value = "/createclient", method = RequestMethod.POST)
    public void saveClient(@RequestParam String jsonfilter) {
	     
	     Client c = new Entreprise("fezr","hui", "hguifa", "fjheiazo", "qdf", "njk");
	     clientDAO.save(c);
	     
    }
	
	
	
	
}
