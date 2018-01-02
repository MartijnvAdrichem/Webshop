package nl.hsleiden.persistence;

import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Account;
import nl.hsleiden.model.Order;
import nl.hsleiden.service.DatabaseService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;
import java.util.Calendar;

@Singleton
public class OrderDAO {

	private Connection dbConnection;

	PreparedStatement createOrderStatement;
	PreparedStatement createOrderRowStatement;


	@Inject
	public OrderDAO(DatabaseService databaseService) {
		this.dbConnection = databaseService.getConnection();
		prepareStatements();

	}

	private void prepareStatements(){
		try{
			createOrderStatement = dbConnection.prepareStatement("INSERT INTO orders(order_acc_id, order_betaaltype, order_bezorgtype, order_date, order_state) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			createOrderRowStatement = dbConnection.prepareStatement("INSERT INTO orderrow(order_id, product_id, amount) VALUES(?,?,?)");
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
return null;
	}

}
