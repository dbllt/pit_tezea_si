package tezea.si.model.business.request;

public enum Priority {
    LOW("Basse"),
    NORMAL("Normale"),
    MEDIUM("Moyenne"),
    HIGH("Haute"),
    VERY_HIGH("Très haute");

    private String message;

    private Priority(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
    
    public String toString() {
    	return message;
    }
}
