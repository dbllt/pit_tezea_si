package tezea.si.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ClientType {
	@JsonProperty("Entreprise")
	COMPANY,
	@JsonProperty("Particulier")
	PERSON,
	@JsonProperty("Association")
	ASSOCIATION,
	@JsonProperty("Collectivité")
	COMMUNITY
}
