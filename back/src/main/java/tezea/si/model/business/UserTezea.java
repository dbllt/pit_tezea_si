package tezea.si.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserTezea {

    private long id;
    private String username;
    private String password;

    @Id
    @GeneratedValue
    @Column(name="id")
    public long getId() {
        return id;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }
    @Column(name="username")
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
