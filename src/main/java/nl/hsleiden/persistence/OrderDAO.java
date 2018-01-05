package nl.hsleiden.persistence;

import io.dropwizard.auth.Auth;
import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Account;
import nl.hsleiden.model.CartRowProduct;
import nl.hsleiden.model.Order;
import nl.hsleiden.model.Product;
import nl.hsleiden.service.DatabaseService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

@Singleton
public class OrderDAO {

	private Connection dbConnection;

	PreparedStatement createOrderStatement;
	PreparedStatement createOrderRowStatement;
	PreparedStatement getOrdersOfAccount;
	PreparedStatement getOrder;
	PreparedStatement getOrderRows;

	@Inject
	public OrderDAO(DatabaseService databaseService) {
		this.dbConnection = databaseService.getConnection();
		prepareStatements();

	}

	private void prepareStatements(){
		try{
			createOrderStatement = dbConnection.prepareStatement("INSERT INTO orders(order_acc_id, order_betaaltype, order_bezorgtype, order_date, order_state) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			createOrderRowStatement = dbConnection.prepareStatement("INSERT INTO orderrow(order_id, product_id, amount) VALUES(?,?,?)");
			getOrdersOfAccount = dbConnection.prepareStatement("SELECT order_id FROM orders WHERE order_acc_id = ?");
			getOrder = dbConnection.prepareStatement("SELECT * from orders WHERE order_id = ?");
			getOrderRows = dbConnection.prepareStatement("SELECT * from orderrow WHERE order_id= ?");
		}
		catch(SQLException e){
			System.out.println("Error in the Prepare Statements (in AccountDao" + e.getStackTrace());
		}
	}

	public HttpResponse createOrder(Order order){
		try {
			createOrderStatement.setInt(1, order.getAccountid());
			createOrderStatement.setString(2, order.getPaymentBank());
			createOrderStatement.setString(3, order.getDelivery());
			createOrderStatement.setDate(4, order.getOrderDate());
			createOrderStatement.setString(5, "in behandeling");
			createOrderStatement.executeUpdate();
			ResultSet resultSet = createOrderStatement.getGeneratedKeys();
			resultSet.next();
			int orderid = resultSet.getInt(1);


			for (int i = 0; i < order.getOrderRows().size(); i++){
				System.out.println("boop");
				createOrderRowStatement.setInt(1, orderid);
				createOrderRowStatement.setInt(2, order.getOrderRows().get(i).getProduct().getId());
				createOrderRowStatement.setInt(3, order.getOrderRows().get(i).getAmount());
				createOrderRowStatement.executeUpdate();
			}
			return new HttpResponse(Response.Status.OK, "Uw bestelling is succesvol afgehandeld en komt zo snel mogelijk bij u thuis");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Oei... Iets is niet helemaal goed gegaan, de bestelling" +
				"is niet afgehandelt!");
	}

	public ArrayList<Order> getHistoryOrders(@Auth Account account){
			ArrayList<Order> allOrders = new ArrayList<>();
		try {
			getOrdersOfAccount.setInt(1, account.getId());
			ResultSet resultSet = getOrdersOfAccount.executeQuery();
			while(resultSet.next()){
				allOrders.add(getOrder(resultSet.getInt("order_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allOrders;
	}

	private Order getOrder(int orderId){
		Order order = new Order();
		try {
			getOrder.setInt(1, orderId);
			ResultSet resultSet = getOrder.executeQuery();
			if(resultSet.next()) {
				order.setId(orderId);
				order.setPaymentBank(resultSet.getString("order_betaaltype"));
				order.setDelivery(resultSet.getString("order_bezorgtype"));
				order.setOrderDate(resultSet.getDate("order_date"));
				order.setOrderState(resultSet.getString("order_state"));
			} else {
				//return empty order because there is nothing in the database
				return new Order();
			}
			ArrayList<CartRowProduct> orderRows = new ArrayList<>();
			getOrderRows.setInt(1, orderId);
			resultSet = getOrderRows.executeQuery();

			while(resultSet.next()){
				CartRowProduct cartRowProduct = new CartRowProduct();
				Product produdct = new Product();
				produdct.setId(resultSet.getInt("product_id"));
				cartRowProduct.setProduct(produdct);
				cartRowProduct.setAmount(resultSet.getInt("amount"));
				orderRows.add(cartRowProduct);
			}
			order.setOrderRows(orderRows);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

}
