package com.dangphattai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="size")
public class Size {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="size_id")
	private int sizeId;
	
	@Column(name="name")
	private String name;
	
	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
