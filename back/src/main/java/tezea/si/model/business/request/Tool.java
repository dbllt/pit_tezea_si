package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Tool {
	@JsonProperty("Port de charge")
	FOR_CARRYING,
	@JsonProperty("Lié à la prestation")
	FOR_SERVICE,
	@JsonProperty("Spécifique")
	SPECIFIC
}
