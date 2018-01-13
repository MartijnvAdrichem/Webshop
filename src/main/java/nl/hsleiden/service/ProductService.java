package nl.hsleiden.service;

import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Product;
import nl.hsleiden.persistence.ProductDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class ProductService {

	private final ProductDAO productDAO;

	@Inject
	public ProductService(ProductDAO productDAO) {

		this.productDAO = productDAO;
	}

	public ArrayList<Product> getProductsByType(String type){

		String enumType = "";

		switch(type){

			case "Stripboeken":
				enumType = "STRIPBOEK";
				break;
			case "Films":
				enumType = "FILM";
				break;
			case "Merchandise":
				enumType = "MERCHANDISE";
				break;
			case "T-shirts":
				enumType = "TSHIRT";
				break;
		}

		return productDAO.getProductsByType(enumType);
	}

	public Product getProductsInformation(int prodid) {
		return productDAO.getProductsInformation(prodid);
	}

	public HttpResponse createProduct(Product product){
		return productDAO.createProduct(product);
	}

	public ArrayList<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}
	public HttpResponse deleteProduct(int id) {
		return productDAO.deleteProduct(id);
	}

	public Product getProduct(int id){
		return this.productDAO.getProductsInformation(id);
	}
	public HttpResponse updateProduct(Product product){
		return this.productDAO.updateProduct(product);
	}
}