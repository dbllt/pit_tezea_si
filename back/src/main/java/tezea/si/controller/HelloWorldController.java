package tezea.si.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;

@RestController
public class HelloWorldController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        
       Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + auth.getName();
    }

}
