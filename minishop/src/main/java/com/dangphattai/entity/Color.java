package com.dangphattai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="color")
public class Color {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="color_id")
	private int colorId;
	
	@Column(name="name")
	private String name;
	
	
	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
