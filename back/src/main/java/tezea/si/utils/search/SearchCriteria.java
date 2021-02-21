package tezea.si.utils.search;

import javax.persistence.metamodel.SingularAttribute;

public class SearchCriteria<T> {
	private SingularAttribute<T, ?> key;
	private SearchOperations operation;
	private Object value;

	public SearchCriteria(SingularAttribute<T, ?> key, SearchOperations operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public String getKey() {
		return key.getName();
	}
	
	public Class<?> getJavaClass() {
		return key.getJavaType();
	}

	public void setKey(SingularAttribute<T, ?> key) {
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
