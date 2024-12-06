package model;

public class products {
	private String pName;
	private Double price;
	private String descriptions;
	public products(String pName, Double price, String descriptions) {
		super();
		this.pName = pName;
		this.price = price;
		this.descriptions = descriptions;
	}
	public String getpName() {
		return pName;
	}
	public Double getPrice() {
		return price;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	
}
