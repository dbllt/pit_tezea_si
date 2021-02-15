package tezea.si.model.admin;

import java.io.Serializable;
import java.util.List;

public class UserTezeaDTO implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
    private List<String> authorities;

    /**
     * Default constructor needed for JSON Parsing
     */
    public UserTezeaDTO() {

    }

    public UserTezeaDTO(String username, String password, List<String> authorities) {
        this.setUsername(username);
        this.setPassword(password);
        this.setAuthorities(authorities);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
