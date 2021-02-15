package tezea.si.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;

@RestController
public class HelloWorldController {

    Logger logger = LogManager.getLogger(getClass());

    @Operation(summary = "A very polite endpoint")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "A simple hello with your name in it") })
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String firstPage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        auth.getAuthorities();
        return "Hello " + auth.getName();
    }

}
