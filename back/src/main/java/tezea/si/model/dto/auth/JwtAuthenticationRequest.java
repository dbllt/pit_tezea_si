package tezea.si.model.dto.auth;

import java.io.Serializable;

/**
 * Format for a JWT request from client
 * 
 * @author Nils Richard
 *
 */
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    /**
     * Default constructor needed for JSON Parsing
     */
    public JwtAuthenticationRequest() {

    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}