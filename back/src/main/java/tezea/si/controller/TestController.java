package tezea.si.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tezea.si.dao.ClientDAO;
import tezea.si.model.Client;

@RestController
public class TestController {
	
	@RequestMapping(("/test"))
    public void test() {
		Client c = new Client("joe");
		ClientDAO daoc = new ClientDAO();
		System.out.println(c.getNom());
		daoc.persist(c);
    }
}
