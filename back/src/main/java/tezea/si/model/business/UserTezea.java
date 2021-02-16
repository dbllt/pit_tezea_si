package tezea.si.model.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tezea.si.utils.StringListConverter;

@Entity
@Table(name = "user")
public class UserTezea {

    private long id;

    private String username;

    private String password;

    private List<String> authorities;

    public UserTezea() {
    }

    @Column(name = "authorities")
    @Convert(converter = StringListConverter.class)
    public List<String> getAuthorities() {
        return authorities;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
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
