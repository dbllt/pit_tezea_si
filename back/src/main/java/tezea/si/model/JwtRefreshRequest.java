package tezea.si.model;

import java.io.Serializable;

public class JwtRefreshRequest implements Serializable {
    private static final long serialVersionUID = 3721266556440867927L;
    
    private String refreshToken;

    /**
     * Default constructor needed for JSON Parsing
     */
    public JwtRefreshRequest() {

    }

    public JwtRefreshRequest(String refreshToken) {
        this.setRefreshToken(refreshToken);
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}
