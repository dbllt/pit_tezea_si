package tezea.si.model.business.request;

import tezea.si.model.business.Employee;

import java.sql.Date;

public class Prestation {
    private long id;
    private Request request;
    private Employee employee;
    private Date date;
    private String details;
    private SatisfactionLevel satisfactionLevel;

}
