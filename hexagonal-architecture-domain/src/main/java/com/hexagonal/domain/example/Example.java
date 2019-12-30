package com.hexagonal.domain.example;

public class Example {

	private Integer entityId;

	private String entityName;

	public Example() {
		super();
	}

	public Example(Integer entityId, String entityName) {
		super();
		this.entityId = entityId;
		this.entityName = entityName;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String toString() {
		return "Example [entityId=" + entityId + ", entityName=" + entityName + "]";
	}

}
