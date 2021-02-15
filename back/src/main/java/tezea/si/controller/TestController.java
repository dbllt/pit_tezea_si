package tezea.si.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tezea.si.dao.ClientDAO;

@RestController
public class TestController {
    
    @Autowired
    ClientDAO clientDAO;
	
	@RequestMapping(("/test"))
    public void test() {
    }
}
