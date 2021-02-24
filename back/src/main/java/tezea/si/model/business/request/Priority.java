package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Priority {
	@JsonProperty("Basse")
    LOW,
	@JsonProperty("Normale")
    NORMAL,
	@JsonProperty("Moyenne")
    MEDIUM,
	@JsonProperty("Haute")
    HIGH,
	@JsonProperty("Très haute")
    VERY_HIGH;
}
