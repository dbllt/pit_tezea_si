package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Tool {

	@JsonProperty("Port de charge")
	FOR_CARRYING("Spécifique"),
	@JsonProperty("Lié à la prestation")
	FOR_SERVICE("Spécifique"),
	@JsonProperty("Spécifique")
	SPECIFIC("Spécifique");


    private String message;

    private Tool(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
