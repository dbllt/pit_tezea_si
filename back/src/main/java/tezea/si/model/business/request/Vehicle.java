package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Vehicle {
	@JsonProperty("Renault Master")
	MASTER,
	@JsonProperty("Camion benne")
	BENNE,
	@JsonProperty("Camion 20 mètres cubes")
	TWENTY_M3
	
}
