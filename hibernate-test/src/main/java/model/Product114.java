package model;
// Generated Oct 14, 2019 12:59:19 PM by Hibernate Tools 5.4.3.Final

/**
 * Product114 generated by hbm2java
 */
public class Product114 implements java.io.Serializable {

	private Long productId;
	private Category114 category114;
	private String code;
	private String name;

	public Product114() {
	}

	public Product114(Category114 category114, String code, String name) {
		this.category114 = category114;
		this.code = code;
		this.name = name;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Category114 getCategory114() {
		return this.category114;
	}

	public void setCategory114(Category114 category114) {
		this.category114 = category114;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
