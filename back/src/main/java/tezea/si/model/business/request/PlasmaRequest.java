package tezea.si.model.business.request;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class PlasmaRequest extends Request{
    private Date date;
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
