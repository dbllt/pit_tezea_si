package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Service {
	@JsonProperty("Prestation")
	SERVICE,
	@JsonProperty("Don")
	DONATION,
	@JsonProperty("Enlèvement et don")
	SERVICE_DONATION,
	@JsonProperty("Plasma")
	PLASMA,
	@JsonProperty("Renseignement")
	INFORMATION

}
