package tezea.si.model.business.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestStatus {
	@JsonProperty("En cours")
    IN_PROGRESS,
    @JsonProperty("Nouveau")
	NEW,
	@JsonProperty("Devis signé")
	ESTIMATION_SIGNED,
	@JsonProperty("Doublon")
	DOUBLE,
	@JsonProperty("Facturé")
	BILLED,
	@JsonProperty("Refusé")
	REFUSED,
	@JsonProperty("Clôturé")
	CLOSED,
	@JsonProperty("Client a appelé")
    CLIENT_CALLED,
	@JsonProperty("Devis en cours")
    ESTIMATION_IN_PROGESS,
	@JsonProperty("Autre")
    OTHER
    
}
