package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.sql.Date;

public class Order {

	@JsonProperty
	int id;

	@JsonProperty
	int accountid;

	@JsonProperty
	String paymentBank;

	@JsonProperty
	String delivery;

	@JsonProperty
	ArrayList<CartRowProduct> orderRows;

	@JsonProperty
	Date orderDate;

	@JsonProperty
	String orderState;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public ArrayList<CartRowProduct> getOrderRows() {
		return orderRows;
	}

	public void setOrderRows(ArrayList<CartRowProduct> orderRows) {
		this.orderRows = orderRows;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
}
