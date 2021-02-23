package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestStatus {
	@JsonProperty("En cours")
    IN_PROGRESS("En cours"),
    @JsonProperty("Nouveau")
	NEW("Nouveau"),
	@JsonProperty("Devis signé")
	ESTIMATION_SIGNED("Devis signé"),
	@JsonProperty("Doublon")
	DOUBLE("Doublon"),
	@JsonProperty("Facturé")
	BILLED("Facturé"),
	@JsonProperty("Refusé")
	REFUSED("Refusé"),
	@JsonProperty("Clôturé")
	CLOSED("Clôturé"),
	@JsonProperty("Client a appelé")
    CLIENT_CALLED("Client a appelé"),
	@JsonProperty("Devis en cours")
    ESTIMATION_IN_PROGESS("Devis en cours"),
	@JsonProperty("Autre")
    OTHER("Autre");
    
    private String message;

    private RequestStatus(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
