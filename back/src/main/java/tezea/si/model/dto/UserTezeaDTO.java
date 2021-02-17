package tezea.si.model.dto;

import java.io.Serializable;
import java.util.List;

import tezea.si.model.business.UserTezea;

/**
 * Class that contains informations (id, username, authorities) concerning a specific UserTezea.
 * Prevent sending password threw network.
 * TODO remove id and authorities.
 * @author Maxime Beucher
 */
public class UserTezeaDTO implements Serializable {
	//@Transient
	private long id;
    private String username;
    //@Transient
    private List<String> authorities;
    
    public UserTezeaDTO() {
    	
    }
    
    public UserTezeaDTO(UserTezea copy) {
    	setId(copy.getId());
    	setUsername(copy.getUsername());
    	setAuthorities(copy.getAuthorities());
    }
    
	public long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
