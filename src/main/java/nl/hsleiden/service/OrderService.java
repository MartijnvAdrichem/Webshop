package nl.hsleiden.service;

import io.dropwizard.auth.Auth;
import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Account;
import nl.hsleiden.model.Order;
import nl.hsleiden.persistence.OrderDAO;
import nl.hsleiden.persistence.ProductDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Calendar;

@Singleton
public class OrderService {

	OrderDAO orderDAO;
	ProductDAO productDAO;

	@Inject
	public OrderService(OrderDAO orderDAO, ProductDAO productDAO){
	this.orderDAO = orderDAO;
	this.productDAO = productDAO;
	}

	public HttpResponse createOrder(Order order){
		order.setOrderDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		return orderDAO.createOrder(order);

	}
	public ArrayList<Order> getHistoryOrders(@Auth Account account){
		ArrayList<Order> orders = orderDAO.getHistoryOrders(account);
		ArrayList<Order> filledOrders = new ArrayList<>();
		for (int i = 0; i < orders.size(); i++) {
			filledOrders.add(productDAO.fillOrderWithProducts(orders.get(i)));

		}
		return orders;
	}
}
