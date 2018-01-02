package nl.hsleiden.resource;

import nl.hsleiden.model.Account;
import nl.hsleiden.model.Order;
import nl.hsleiden.service.OrderService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@com.google.inject.Singleton
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

	OrderService orderService;

	@Inject
	public OrderResource(OrderService orderService){
		this.orderService = orderService;
	}

	@POST
	@Path("create")
	public void createOrder(Order order) {
		orderService.createOrder(order);
	}
}
