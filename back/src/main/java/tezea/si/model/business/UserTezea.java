package tezea.si.model.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserTezea {

    private long id;
    private String username;
    private String password;
    
    
    public UserTezea() {}
    
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
