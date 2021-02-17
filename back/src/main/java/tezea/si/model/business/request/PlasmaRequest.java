package tezea.si.model.business.request;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class PlasmaRequest extends Request{
    private Date appointmentDate;
    
    public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

}
