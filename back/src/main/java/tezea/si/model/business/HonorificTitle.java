package tezea.si.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HonorificTitle {
	@JsonProperty("Mr")
    MR("Mr"),
    @JsonProperty("Mme")
    MME("Mme");
	
    private String message;

    private HonorificTitle(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
