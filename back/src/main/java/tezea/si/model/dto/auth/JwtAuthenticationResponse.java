package tezea.si.model.dto.auth;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * Classic response for a JWT authenticated request
 * 
 * @author Nils Richard
 *
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 6098474392852070760L;
    private final String token;
    private final String refreshToken;
    private final Long expiresIn;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtAuthenticationResponse(String token, String refreshToken, Long expiresIn,
            Collection<? extends GrantedAuthority> authorities) {
        super();
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

}