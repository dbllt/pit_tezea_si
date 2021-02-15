package tezea.si.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import tezea.si.model.admin.UserTezeaDTO;
import tezea.si.service.JwtUserDetailsService;
import tezea.si.utils.auth.GrantedAutorities;
import tezea.si.utils.errors.UserAlreadyExistsException;

@RestController
public class AdminController {
    Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private JwtUserDetailsService userDetailsService;

    /**
     * Entry point to register, there is an exception in configuration to allow
     * unauthenticated requests to this entry point.
     * 
     * Try to create new account using a unique username and password. Succeed if
     * username has not been used before.
     * 
     * @param authenticationRequest represents specified username and password
     * @return
     * @throws Exception
     */
    @Operation(summary = "Creating a new user")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody UserTezeaDTO newUser) throws Exception {
        if(newUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST");
        checkIfAdmin();
        
        try {
            userDetailsService.save(newUser.getUsername(), newUser.getPassword(), newUser.getAuthorities());
        } catch (UserAlreadyExistsException e) {
            // attempt failed
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ALREADY_USED_USERNAME");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
        // return createAuthenticationToken(authenticationRequest);
    }

    private void checkIfAdmin() throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<String> authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        
        if (!authorities.contains(GrantedAutorities.ADMIN)) {
            throw new AccessDeniedException("AUTHORITY_REQUIRED");
        }
    }
}
