package nl.hsleiden.service;

import nl.hsleiden.model.Account;
import nl.hsleiden.model.Order;
import nl.hsleiden.persistence.OrderDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Calendar;

@Singleton
public class OrderService {

	OrderDAO orderDAO;

	@Inject
	public OrderService(OrderDAO orderDAO){
	this.orderDAO = orderDAO;
	}

	public void createOrder(Order order){
		order.setOrderDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		orderDAO.createOrder(order);

	}
}
