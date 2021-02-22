package tezea.si.utils.search;

import javax.persistence.metamodel.SingularAttribute;

public class SearchCriteria {
	private SingularAttribute<?, ?> key;
	private SingularAttribute<?, ?> nestedKey;
	private boolean hasNestedKey;
	private SearchOperations operation;
	private Object value;

	public SearchCriteria(SingularAttribute<?, ?> key, SearchOperations operation,
			Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(SingularAttribute<?, ?> key, SingularAttribute<?, ?> nestedKey,
			SearchOperations operation, Object value) {
		this.key = key;
		this.nestedKey = nestedKey;
		hasNestedKey = true;
		this.operation = operation;
		this.value = value;
	}

	public boolean hasNestedKey() {
		return hasNestedKey;
	}

	public String getKey() {
		return key.getName();
	}

	public String getNestedKey() {
		return nestedKey.getName();
	}

	public Class<?> getJavaClass() {
		if (hasNestedKey()) {
			return nestedKey.getJavaType();
		}
		return key.getJavaType();
	}

	public void setKey(SingularAttribute<?, ?> key) {
		this.key = key;
	}

	public void setNestedKey(SingularAttribute<?, ?> nestedKey) {
		this.nestedKey = nestedKey;
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
