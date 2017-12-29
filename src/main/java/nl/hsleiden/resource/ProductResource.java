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
	@Path("/{ids}")
	@JsonView(View.Public.class)
	public ArrayList<Product> getProductInformation(@PathParam("ids") String idString) {
		System.out.println(idString);
		String[] productIds = idString.split("-");
		ArrayList<Product> products = new ArrayList<>();
		for (int i = 0; i < productIds.length; i++) {
			products.add(productService.getProductsInformation(Integer.parseInt(productIds[i])));
		}
		return products;
	}


}