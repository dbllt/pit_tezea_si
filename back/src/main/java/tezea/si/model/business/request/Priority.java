package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Priority {
	@JsonProperty("Basse")
    LOW("Basse"),
	@JsonProperty("Normale")
    NORMAL("Normale"),
	@JsonProperty("Moyenne")
    MEDIUM("Moyenne"),
	@JsonProperty("Haute")
    HIGH("Haute"),
	@JsonProperty("Très haute")
    VERY_HIGH("Très haute");

    private String message;

    private Priority(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
