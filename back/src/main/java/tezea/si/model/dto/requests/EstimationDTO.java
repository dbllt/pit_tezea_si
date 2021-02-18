package tezea.si.model.dto.requests;

import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.Estimation;
import tezea.si.model.business.request.Request;
import tezea.si.model.dto.UserTezeaDTO;
import tezea.si.utils.RequestToDTOConverter;

import java.io.Serializable;
import java.sql.Date;

public class EstimationDTO implements Serializable {
    private long id;
    private long requestId;
    private UserTezeaDTO estimationResponsable;
    private Date date;
    private double amount;
    private String materialEstimation;

    public EstimationDTO(Estimation copy){
        this.id = copy.getId();
        this.requestId = copy.getRequest().getId();
        this.estimationResponsable = UserTezeaDTO.copy(copy.getEstimationResponsable());
        this.date = copy.getDate();
        this.amount = copy.getAmount();
        this.materialEstimation = copy.getMaterialEstimation();
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

    public UserTezeaDTO getEstimationResponsable() {
        return estimationResponsable;
    }

    public void setEstimationResponsable(UserTezeaDTO estimationResponsable) {
        this.estimationResponsable = estimationResponsable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMaterialEstimation() {
        return materialEstimation;
    }

    public void setMaterialEstimation(String materialEstimation) {
        this.materialEstimation = materialEstimation;
    }
}
