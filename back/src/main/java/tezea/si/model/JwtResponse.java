package tezea.si.model;

import java.io.Serializable;

/**
 * Classic response for a JWT authenticated request
 * 
 * @author Nils Richard
 *
 */
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 6098474392852070760L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}