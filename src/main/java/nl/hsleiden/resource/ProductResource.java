package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import nl.hsleiden.View;
import nl.hsleiden.model.Product;
import nl.hsleiden.service.AccountService;
import nl.hsleiden.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Singleton
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	private final ProductService productService;


	@Inject
	public ProductResource(ProductService productService) {
		this.productService = productService;

	}

	@GET
	@Path("/list/{type}")
	@JsonView(View.Public.class)
	public ArrayList<Product> getProducts(@PathParam("type") String type) {

		return productService.getProductsByType(type);
	}

	@GET
	@Path("/{id]")
	@JsonView(View.Public.class)
	public Product getProductInformation(@PathParam("id") int id) {

		return productService.getProductsInformation(id);
	}


}