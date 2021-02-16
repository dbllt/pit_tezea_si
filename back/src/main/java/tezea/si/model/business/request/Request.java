package tezea.si.model.business.request;

import tezea.si.model.business.Client;
import tezea.si.model.business.Site;
import tezea.si.model.business.UserTezea;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public abstract class Request {

    private long id;
    private Date date;
    private Site site;
    //TODO : Utiliser idUserTezea plutôt que d'avoir l'objet complet ?
    private UserTezea responsable;
    private Client client;
    private Priority priority;
    private String description;
    private RequestStatus status;

    private UserTezea closedBy;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public UserTezea getResponsable() {
        return responsable;
    }

    public void setResponsable(UserTezea responsable) {
        this.responsable = responsable;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public UserTezea getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(UserTezea closedBy) {
        this.closedBy = closedBy;
    }
}
