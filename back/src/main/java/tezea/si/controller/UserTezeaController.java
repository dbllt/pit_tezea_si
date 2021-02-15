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
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.UserTezea;

@RestController
public class UserTezeaController {
	
	@Autowired
    UserTezeaDAO userDAO;
	
	@Operation(summary = "get users")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get users") })
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UserTezea>> getClients() {
	     Iterable<UserTezea> userIt = userDAO.findAll();
	     return ResponseEntity.ok(userIt);
    }
	
	
	@Operation(summary = "save user")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create user based on json") })
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public void saveEntreprise(@RequestBody UserTezea u) {
		userDAO.save(u);
	     
    }
	
	
}
