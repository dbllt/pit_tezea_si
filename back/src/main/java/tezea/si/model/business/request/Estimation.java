package tezea.si.model.business.request;

import tezea.si.model.business.UserTezea;

import java.sql.Date;

public class Estimation {
    private long id;
    //TODO : Utiliser idUserTezea plutôt que d'avoir l'objet complet ?
    private Request request;
    private UserTezea estimationResponsable;
    private Date date;
    private double amount;
    private String materialEstimation;
}
