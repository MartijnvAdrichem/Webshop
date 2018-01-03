package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartRowProduct {

	public CartRowProduct() {

	}

	@JsonProperty
	Product product;

	@JsonProperty
	int amount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}



}
