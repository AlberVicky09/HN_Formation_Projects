package com.HNServices.HNProfile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TypeUser")
public class TypeUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="typeName", unique=true)
	private String typeName;
		
	public TypeUser() {}

	public TypeUser(String type) {
		super();
		this.typeName = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String type) {
		this.typeName = type;
	}

	@Override
	public String toString() {
		return "TypeUser [id=" + id + ", typeName=" + typeName + "]";
	}
	
}
