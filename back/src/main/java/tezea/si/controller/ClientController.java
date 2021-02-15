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
import tezea.si.dao.ClientDAO;
import tezea.si.model.business.Client;
import tezea.si.model.business.Entreprise;
import tezea.si.model.business.Particulier;

@RestController
public class ClientController {
	
	@Autowired
    ClientDAO clientDAO;
	
	@Operation(summary = "get clients based on json filter")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get clients based on json filter") })
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Iterable<Client>> getClients() {
	     Iterable<Client> clientsIt = clientDAO.findAll();
	     return ResponseEntity.ok(clientsIt);
    }
	
	
	@Operation(summary = "save client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create entreprise client based on json") })
    @RequestMapping(value = "/createentreprise", method = RequestMethod.POST)
    public void saveEntreprise(@RequestBody Entreprise c) {
	     clientDAO.save(c);
	     
    }
	
	@Operation(summary = "save client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create client based on json") })
    @RequestMapping(value = "/createparticulier", method = RequestMethod.POST)
    public void saveClient(@RequestBody Particulier c) {
	     clientDAO.save(c);
	     
    }
	
	
	
	
}
