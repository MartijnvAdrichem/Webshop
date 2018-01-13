package nl.hsleiden.persistence;

import com.google.inject.Inject;
import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.CartRowProduct;
import nl.hsleiden.model.Order;
import nl.hsleiden.model.Product;
import nl.hsleiden.service.DatabaseService;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Singleton
public class ProductDAO {


	private PreparedStatement getProductsbyTypeStatement;
	private PreparedStatement getProductsInformationStatement;
	private PreparedStatement createProductsStatement;
	private PreparedStatement getAllProductsStatement;
	private PreparedStatement deleteProductStatement;
	private PreparedStatement updateProductStatement;

	public Connection dbConnection;

	@Inject
	public ProductDAO(DatabaseService databaseService) {
		this.dbConnection = databaseService.getConnection();
		prepareStatements();

	}

	private void prepareStatements() {
		try {
			getProductsbyTypeStatement = dbConnection.prepareStatement("SELECT * FROM product WHERE prod_type = CAST(? AS producttype) AND prod_actief = TRUE ");
			getProductsInformationStatement = dbConnection.prepareStatement("SELECT * FROM product WHERE prod_id = ?");
			createProductsStatement = dbConnection.prepareStatement("INSERT INTO product(prod_naam, prod_beschrijving, prod_prijs, prod_image, prod_type) VALUES (?,?,?,?,CAST(? AS producttype))");
			getAllProductsStatement = dbConnection.prepareStatement("SELECT * FROM product WHERE prod_actief = TRUE");
			deleteProductStatement = dbConnection.prepareStatement("UPDATE product SET prod_actief = false WHERE prod_id = ?");
			updateProductStatement = dbConnection.prepareStatement("UPDATE product SET prod_naam = ?, prod_beschrijving = ?, prod_prijs = ?, prod_image = ?, prod_type = CAST(? AS producttype) WHERE prod_id = ?");
		} catch (SQLException e) {
			System.out.println("Error in the Prepare Statements (in AccountDao" + e.getStackTrace());
		}
	}

	public ArrayList<Product> getProductsByType(String type) {
		try {
			System.out.println("Finding products for type " + type);

		getProductsbyTypeStatement.setString(1, type.toUpperCase());

			ResultSet resultSet = getProductsbyTypeStatement.executeQuery();
			ArrayList<Product> products = makeProductsArray(resultSet);
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Product> getAllProducts() {
		try {
			ResultSet resultSet = getAllProductsStatement.executeQuery();
			ArrayList<Product> products = makeProductsArray(resultSet);
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Product> makeProductsArray(ResultSet resultSet) throws SQLException {
		ArrayList<Product> products = new ArrayList<>();
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
	}

	public HttpResponse deleteProduct(int id) {
		try {
			deleteProductStatement.setInt(1, id);
			deleteProductStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Product succesvol deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bij het deleten van een product");
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

	public HttpResponse createProduct(Product product){
		try {
			createProductsStatement.setString(1, product.getName());
			createProductsStatement.setString(2, product.getDescription());
			createProductsStatement.setDouble(3, product.getPrice());
			createProductsStatement.setString(4, product.getImageURL());
			createProductsStatement.setString(5, product.getType());
			createProductsStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Product toegevoegd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bij product toevoegen");
	}

	public HttpResponse updateProduct(Product product){
		try {
			updateProductStatement.setString(1,product.getName());
			updateProductStatement.setString(2,product.getDescription());
			updateProductStatement.setDouble(3,product.getPrice());
			updateProductStatement.setString(4,product.getImageURL());
			updateProductStatement.setString(5,product.getType());
			updateProductStatement.setInt(6,product.getId());
			updateProductStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Product is bijgewerkt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bi het product bijwerken");
	}
}
