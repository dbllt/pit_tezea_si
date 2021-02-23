package tezea.si.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Site {
	@JsonProperty("Bois")
	BOIS_PALETTES("Bois"),
	@JsonProperty("Couture")
	COUTURE("Couture"),
	@JsonProperty("Tri démantèlement")
	TRI_DEMANTELEMENT("Tri démantèlement"),
	@JsonProperty("Recyclerie")
	RECYCLERIE("Recyclerie"),
	@JsonProperty("Dons enlèvements")
	DONS_ENLEVEMENT("Dons enlèvements"),
	@JsonProperty("Estimateur")
	ESTIMATEUR("Estimateur"),
	@JsonProperty("Conciergerie")
	CONCIERGERIE("Conciergerie");
	
    private String message;

    private Site(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
