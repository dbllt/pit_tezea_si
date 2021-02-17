package tezea.si.utils.search;

public class SearchCriteria {
	private String key;
	private SearchOperations operation;
	private Object value;

	public SearchCriteria(String key, SearchOperations operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SearchOperations getOperation() {
		return operation;
	}

	public void setOperation(SearchOperations operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
