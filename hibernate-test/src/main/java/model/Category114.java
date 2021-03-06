package model;
// Generated Oct 14, 2019 12:59:19 PM by Hibernate Tools 5.4.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Category114 generated by hbm2java
 */
public class Category114 implements java.io.Serializable {

	private Long categoryId;
	private String name;
	private Set product114s = new HashSet(0);

	public Category114() {
	}

	public Category114(String name) {
		super();
		this.name = name;
	}

	public Category114(String name, Set product114s) {
		this.name = name;
		this.product114s = product114s;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getProduct114s() {
		return this.product114s;
	}

	public void setProduct114s(Set product114s) {
		this.product114s = product114s;
	}

	@Override
	public String toString() {
		return "Category114 [categoryId=" + categoryId + ", name=" + name + ", product114s=" + product114s + "]";
	}

}
