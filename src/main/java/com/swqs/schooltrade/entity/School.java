package com.swqs.schooltrade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.swqs.schooltrade.util.BaseEntity;

@Entity
public class School extends BaseEntity {

	String name;

	@Column(unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
