package tezea.si.model.auth;

import java.io.Serializable;

/**
 * Classic response for a JWT authenticated request
 * 
 * @author Nils Richard
 *
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 6098474392852070760L;
    private final String jwttoken;
    private final String refreshToken;

    public JwtAuthenticationResponse(String jwttoken, String refreshToken) {
        this.jwttoken = jwttoken;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }
}