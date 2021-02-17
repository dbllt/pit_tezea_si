package tezea.si.model.dto.requests;

import tezea.si.model.business.request.PlasmaRequest;
import tezea.si.model.business.request.Request;

import java.io.Serializable;
import java.sql.Date;

public class PlasmaRequestDTO extends RequestDTO implements Serializable {
    private Date appointmentDate;

    public PlasmaRequestDTO(Request copy){
        super(copy);
        assert(copy instanceof PlasmaRequest);

        PlasmaRequest r = (PlasmaRequest) copy;
        this.appointmentDate = r.getAppointmentDate();
    }
}
