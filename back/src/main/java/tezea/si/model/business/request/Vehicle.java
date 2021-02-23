package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Vehicle {
	@JsonProperty("Renault Master")
	MASTER("Renault Master"),
	@JsonProperty("Camion benne")
	BENNE("Camion benne"),
	@JsonProperty("Camion 20 mètres cubes")
	TWENTY_M3("Camion 20 mètres cubes");

    private String message;

    private Vehicle(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
	
}
