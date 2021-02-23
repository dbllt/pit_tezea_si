package tezea.si.model.dto.admin;

import java.io.Serializable;

public class RemoveUserRequest implements Serializable {

    private static final long serialVersionUID = 2781255784385730608L;
    private String username;

    /**
     * Default constructor needed for JSON Parsing
     */
    public RemoveUserRequest() {
    }

    public RemoveUserRequest(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
