package nl.hsleiden.persistence;

import com.google.inject.Inject;
import nl.hsleiden.model.CartRowProduct;
import nl.hsleiden.model.Order;
import nl.hsleiden.model.Product;
import nl.hsleiden.service.DatabaseService;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Singleton
public class ProductDAO {


	private PreparedStatement getProductsbyTypeStatement;
	private PreparedStatement getProductsInformationStatement;

	public Connection dbConnection;

	@Inject
	public ProductDAO(DatabaseService databaseService) {
		this.dbConnection = databaseService.getConnection();
		prepareStatements();

	}

	private void prepareStatements() {
		try {
			getProductsbyTypeStatement = dbConnection.prepareStatement("SELECT * FROM product WHERE prod_type = CAST(? AS producttype)");
			getProductsInformationStatement = dbConnection.prepareStatement("SELECT * FROM product WHERE prod_id = ?");
		} catch (SQLException e) {
			System.out.println("Error in the Prepare Statements (in AccountDao" + e.getStackTrace());
		}
	}

	public ArrayList<Product> getProductsByType(String type) {
		try {
			System.out.println("Finding products for type " + type);
			ArrayList<Product> products = new ArrayList<>();

		getProductsbyTypeStatement.setString(1, type.toUpperCase());

			ResultSet resultSet = getProductsbyTypeStatement.executeQuery();
			while(resultSet.next()){
				Product product = new Product();
				product.setId(resultSet.getInt("prod_id"));
				product.setDescription(resultSet.getString("prod_beschrijving"));
				product.setName(resultSet.getString("prod_naam"));
				product.setImageURL(resultSet.getString("prod_image"));
				product.setType(resultSet.getString("prod_type"));
				product.setPrice(resultSet.getDouble("prod_prijs"));
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product getProductsInformation(int prodid){
			try {
				getProductsInformationStatement.setInt(1, prodid);
			ResultSet resultSet = getProductsInformationStatement.executeQuery();
			if (resultSet.next()){
				System.out.println("Making product");
				Product product = makeProduct(resultSet);
				return product;
			}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	public Product makeProduct(ResultSet resultSet) throws SQLException{

		Product product = new Product();
		product.setId(resultSet.getInt("prod_id"));
		product.setDescription(resultSet.getString("prod_beschrijving"));
		product.setName(resultSet.getString("prod_naam"));
		product.setImageURL(resultSet.getString("prod_image"));
		product.setType(resultSet.getString("prod_type"));
		product.setPrice(resultSet.getDouble("prod_prijs"));
		return product;
	}


	public Order fillOrderWithProducts(Order order){

		for (int i = 0; i < order.getOrderRows().size() ; i++) {
			Product newProduct = getProductsInformation(order.getOrderRows().get(i).getProduct().getId());
			order.getOrderRows().get(i).setProduct(newProduct);
		}
		return order;
	}
}
