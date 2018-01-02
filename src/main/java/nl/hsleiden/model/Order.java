package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Order {

	@JsonProperty
	Account account;

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





}
