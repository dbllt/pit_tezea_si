package tezea.si.model.dto.requests;

import tezea.si.model.business.RequestEmployee;
import tezea.si.model.business.request.Prestation;
import tezea.si.model.business.request.Request;
import tezea.si.model.business.request.SatisfactionLevel;

import java.io.Serializable;
import java.sql.Date;

public class PrestationDTO implements Serializable {

    private long id;
    private long requestId;
    private RequestEmployee employee;
    private Date date;
    private String details;
    private SatisfactionLevel satisfactionLevel;

    public PrestationDTO(Prestation copy){
        this.id = copy.getId();
        this.requestId = copy.getRequest().getId();
        this.employee = copy.getEmployee();
        this.date = copy.getDate();
        this.details = copy.getDetails();
        this.satisfactionLevel = copy.getSatisfactionLevel();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public RequestEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(RequestEmployee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public SatisfactionLevel getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(SatisfactionLevel satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }
}
