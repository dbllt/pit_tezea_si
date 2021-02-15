package tezea.si.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.model.JwtAuthenticationRequest;
import tezea.si.model.JwtAuthenticationResponse;
import tezea.si.model.JwtRefreshRequest;
import tezea.si.model.JwtRefreshResponse;
import tezea.si.service.JwtUserDetailsService;
import tezea.si.service.RefreshTokenService;
import tezea.si.utils.JwtTokenUtil;

/**
 * Defines the authentication entry points
 * 
 * @author Nils Richard
 *
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class JwtAuthenticationController {

    Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    /**
     * Entry point to authenticate, there is an exception in configuration to allow
     * unauthenticated requests to this entry point
     * 
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @Operation(summary = "Authenticate and get an access token")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Your access token"),
            @ApiResponse(responseCode = "401", description = "If your credentials are incorrect") })
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        refreshTokenService.saveRefreshToken(refreshToken);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token, refreshToken));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    /**
     * If given refresh token is valid, returns a new access token
     * 
     * @param refreshRequest
     * @return
     * @throws Exception
     */
    @Operation(summary = "Refresh an access token")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Your access token"),
            @ApiResponse(responseCode = "401", description = "If refresh token is invalid") })
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAccess(@RequestBody JwtRefreshRequest refreshRequest) throws Exception {
        if (refreshTokenService.isValid(refreshRequest.getRefreshToken())) {
            final String username = jwtTokenUtil.getUsernameFromToken(refreshRequest.getRefreshToken());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtRefreshResponse(token));
        } else {
            throw new BadCredentialsException("INVALID_REFRESH_TOKEN");
        }

    }

    /**
     * Invalidates the given refresh token
     * 
     * @param refreshRequest
     * @return
     * @throws Exception
     */
    @Operation(summary = "Invalidate a refresh token (logout)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Refresh token has been invalidated (you are logged out)") })
    @RequestMapping(value = "/token", method = RequestMethod.PUT)
    public ResponseEntity<?> invalidateRefreshToken(@RequestBody JwtRefreshRequest refreshRequest) throws Exception {
        refreshTokenService.invalidate(refreshRequest.getRefreshToken());

        return ResponseEntity.noContent().build();
    }
    
    /**
     * Entry point to register, there is an exception in configuration to allow
     * unauthenticated requests to this entry point.
     * 
     * Try to create new account using a unique username and password.
     * Succeed if username has not been used before.
     * 
     * @param authenticationRequest represents specified username and password
     * @return
     * @throws Exception
     */
    @Operation(summary = "Creating a new user")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {        
        try {
            userDetailsService.save(
            		authenticationRequest.getUsername(),
            		authenticationRequest.getPassword());
        } catch (Exception e) {
            // attempt failed
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ALREADY_USED_USERNAME");
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
        //return createAuthenticationToken(authenticationRequest);
    }

}