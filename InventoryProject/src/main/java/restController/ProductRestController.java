package com.inventory.restController;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ProductException;
import model.Product;
import service.ProductServiceImpl;


@RestController
@RequestMapping(value = "/ct")
public class ProductRestController {
	
	private static final Log logger = LogFactory.getLog(ProductRestController.class);

	@Autowired
	private ProductServiceImpl service;

	@PostMapping("/addProduct")
	public ResponseEntity<String> addProductDetails(@RequestBody Product productDetails) {
		
		logger.info("\n\n ProductRestController -- addProductDetails() method started \n");
		if(service.isProductsDetailValid(productDetails)) {
			service.addProductDetails(productDetails);
			return new ResponseEntity<String>("Product successfully added", HttpStatus.CREATED);
		}
		logger.info("\n\n ProductRestController -- addProductDetails() method ended \n");
		return new ResponseEntity<String>("Product cant added because of some date or Id issue", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/getAllproducts")
	public ResponseEntity<List<Product>> getAllProductsDetails() throws ProductException {
		
		logger.info("\n\n ProductRestController -- getAllProductsDetails() method started \n");
		List<Product> productList = service.listProductDetails();
		logger.info("\n\n ProductRestController -- getAllProductsDetails() method ended \n");
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getProduct/{productDesc}")
	public ResponseEntity<Product> searchProductByDescription(@PathVariable String productDesc) throws ProductException {
		//if (service.isProductExist(productDesc)) {
		Product productModel= null;
		try {
			productModel = service.searchProductByDescription(productDesc);
			
		}catch (ProductException e) {
			logger.error("\n\n Description of exception -->>  "+e);
		}
		return new ResponseEntity<>(productModel, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) throws ProductException {
		if (service.isProductExistByID(productId)) {
			service.deleteProductDetails(productId);
			return new ResponseEntity<>("Product deleted successfully ", HttpStatus.OK);
		}
		return new ResponseEntity<>("Provided product is not available for deletion ", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/updateProduct/{productId}")
	public ResponseEntity<String> updateProductDetails(@PathVariable Integer productId,
			@RequestBody Product productModel) throws ProductException {
		if (service.isProductExistByID(productId)) {
			service.updateProductDetails(productId, productModel);
			return new ResponseEntity<>("Product Updated successfully ", HttpStatus.OK);
		}
		return new ResponseEntity<>(" Product Not found ", HttpStatus.NOT_FOUND);

	}

}