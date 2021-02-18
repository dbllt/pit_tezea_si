package tezea.si.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.dao.ClientDAO;
import tezea.si.model.business.Client;
import tezea.si.model.business.Enterprise;
import tezea.si.model.business.Individual;

@RestController
public class ClientController {
	
	@Autowired
    ClientDAO clientDAO;
	
	@Operation(summary = "get clients based on json filter")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get clients") })
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Client>> getClients() {
	     Iterable<Client> clientsIt = clientDAO.findAll();
	     return ResponseEntity.ok(clientsIt);
    }
	
	
	@Operation(summary = "save client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create entreprise client based on json") })
    @RequestMapping(value = "/createentreprise", method = RequestMethod.POST)
    public void saveEntreprise(@RequestBody Enterprise c) {
	     clientDAO.save(c);
	     
    }
	
	@Operation(summary = "save client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create client based on json") })
    @RequestMapping(value = "/createparticulier", method = RequestMethod.POST)
    public void saveClient(@RequestBody Individual c) {
	     clientDAO.save(c);
	     
    }
	
	@Operation(summary = "edit client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "modify client based on json") })
    @RequestMapping(value = "/editclient/{id}", method = RequestMethod.POST)
    public void modifyClient(@RequestBody Client c, @PathVariable Long id) {
	     Optional<Client> client = clientDAO.findById(id);
	     if(client.get()!=null) {
	    	 client.get().updateFrom(c);
	    	 clientDAO.save(client.get());
	     }
	     
    }
	
	
	
	
}
