package tezea.si.model.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import tezea.si.model.business.request.Estimation;
import tezea.si.model.business.request.Request;
import tezea.si.utils.StringListConverter;

@Entity
@Table(name = "user")
public class UserTezea {
    private Long id;
    private String username;
    private String password;
    private List<String> authorities;

    private List<Request> closedBy;
    private List<Request> responsabilities;
	private List<Site> sites;
    private List<Estimation> estimations;

    @PreRemove
    public void removeDependencies() {
    	closedBy.forEach(e -> e.setClosedBy(null));
    	responsabilities.forEach(e -> e.setResponsable(null));
    	estimations.forEach(e -> e.setEstimationResponsable(null));
    }
    
    public UserTezea() {
    }

    @Column(name = "authorities")
    @Convert(converter = StringListConverter.class)
    public List<String> getAuthorities() {
        return authorities;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    public Long getId() {
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
    
	@OneToMany(mappedBy="closedBy", fetch = FetchType.LAZY)
	public List<Request> getClosedBy() {
		return closedBy;
	}
	
	@OneToMany(mappedBy="estimationResponsable", fetch = FetchType.LAZY)
	public List<Estimation> getEstimations() {
		return estimations;
	}

	@ElementCollection
	public List<Site> getSites() {
		return sites;
	}

	@OneToMany(mappedBy="responsable", fetch = FetchType.LAZY)
	public List<Request> getResponsabilities() {
		return responsabilities;
	}
	
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setClosedBy(List<Request> resp) {
    	this.closedBy = resp;
    }
    
    public void setSites(List<Site> sites) {
    	this.sites = sites;
    }
    
    public void setEstimations(List<Estimation> estimations) {
    	this.estimations = estimations;
    }
    
    public void setResponsabilities(List<Request> resp) {
    	this.responsabilities = resp;
    }

    
}
