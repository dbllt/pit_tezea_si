package tezea.si.model.dto.requests;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Transient;

import tezea.si.model.business.Site;
import tezea.si.model.business.request.Priority;
import tezea.si.model.business.request.Request;
import tezea.si.model.business.request.RequestStatus;
import tezea.si.model.dto.ClientSearchDTO;
import tezea.si.model.dto.UserTezeaDTO;
import tezea.si.utils.ClientToDTOConverter;

public class RequestDTO implements Serializable {

    private long id;
    private Date date;
    private Site site;
    private UserTezeaDTO responsable;
    private ClientSearchDTO client;
    private Priority priority;
    private String description;
    private RequestStatus status;
    private UserTezeaDTO closedBy;
	private String inherits;

    public RequestDTO(Request copy){
    	this.inherits = copy.getDiscriminator();
        this.id = copy.getId();
        this.date = copy.getDate();
        this.site = copy.getSite();
        this.responsable = UserTezeaDTO.copy(copy.getResponsable());
        this.client = ClientToDTOConverter.pack(copy.getClient());
        this.priority = copy.getPriority();
        this.description = copy.getDescription();
        this.status = copy.getStatus();
        this.closedBy = UserTezeaDTO.copy(copy.getClosedBy());
    }

    public RequestDTO(){}
    
    public String getDiscriminator() {
    	return inherits;
    }
    
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

    public UserTezeaDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(UserTezeaDTO responsable) {
        this.responsable = responsable;
    }

    public ClientSearchDTO getClient() {
        return client;
    }

    public void setClient(ClientSearchDTO client) {
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

    public UserTezeaDTO getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(UserTezeaDTO closedBy) {
        this.closedBy = closedBy;
    }
}
