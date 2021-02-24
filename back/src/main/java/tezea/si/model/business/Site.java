package tezea.si.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Site {
	@JsonProperty("Bois")
	BOIS_PALETTES,
	@JsonProperty("Couture")
	COUTURE,
	@JsonProperty("Tri démantèlement")
	TRI_DEMANTELEMENT,
	@JsonProperty("Recyclerie")
	RECYCLERIE,
	@JsonProperty("Dons enlèvements")
	DONS_ENLEVEMENT,
	@JsonProperty("Estimateur")
	ESTIMATEUR,
	@JsonProperty("Conciergerie")
	CONCIERGERIE;

}
