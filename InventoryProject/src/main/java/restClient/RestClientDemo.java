package restClient;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

import model.Product;

public class RestClientDemo {
	
	static RestTemplate restTemplate = new RestTemplate();

	private static final Log logger = LogFactory.getLog(RestClientDemo.class);
	
	private static final String URL = "http://localhost:1010/ct/";
	private static String desc = "Cooler";
	private static Integer productId = 1;

	public static void main(String[] args) {

		LocalDate mf = LocalDate.of(2023, 1, 13);
		LocalDate exp = LocalDate.of(2023, 1, 13);
		Product productModel = new Product(11, "Charger", 33.00f, 10.00, mf, exp);

		deleteProduct(productId);
		
	}

	private static void deleteProduct(Integer productId) {
		restTemplate.delete(URL+"/deleteProduct/"+productId, productId);
		logger.error("\n\n Rest template deleted the product successfully");
	}

	private static void searchProductByDescription(String desc) {
		String response = restTemplate.getForObject(URL+ "/getProduct/"+desc,String.class);
		logger.error("\n\n Rest template fetching the product -->> \n"+response+"\n");
	}

	private static void getAllProductsDetails() {
		List<Product> productsList = restTemplate.getForObject(URL+ "/getAllproducts", ArrayList.class);
		logger.info("\n\n Rest template fetching the product -->> \n"+productsList+"\n");
	}

	private static void addProductDetails(Product productModel) {
		String response = restTemplate.postForObject(URL + "/addProduct", productModel, String.class);
		logger.info("\n\n Rest template adding the product -->> \n"+response+"\n\n");	
		
	}
}

