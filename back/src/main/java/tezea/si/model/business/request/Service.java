package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Service {
	@JsonProperty("Prestation")
	SERVICE("Prestation"),
	@JsonProperty("Don")
	DONATION("Don"),
	@JsonProperty("Enlèvement et don")
	SERVICE_DONATION("Enlèvement et don"),
	@JsonProperty("Plasma")
	PLASMA("Plasma"),
	@JsonProperty("Renseignement")
	INFORMATION("Renseignement");

    private String message;

    private Service(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
