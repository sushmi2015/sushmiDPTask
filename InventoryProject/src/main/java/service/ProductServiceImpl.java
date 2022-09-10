package service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProductDao;
import entity.ProductEntity;
import exception.ProductException;
import model.Product;

@Transactional
@Service
public class ProductServiceImpl implements ProductServiceI {

	private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);

	@Autowired
	private ProductDao dao;

	public ProductEntity addProductDetails(Product productModel) {

		logger.info("\n\n ProductServiceImpl -- addProductDetails() method started \n");

		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(productModel.getId());
		productEntity.setDescription(productModel.getDescription());
		productEntity.setWeight(productModel.getWeight());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setManufacturingDate(productModel.getManufacturingDate());
		productEntity.setExpiryDate(productModel.getExpiryDate());

		ProductEntity productEntity2 = dao.save(productEntity);

		logger.info("\n\n ProductServiceImpl -- addProductDetails() method ended \n");

		return productEntity2;
	}
	
	public boolean isProductExist(String productDesc) {
		Optional<ProductEntity> p = dao.findByDescription(productDesc);
		if(p.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	public List<Product> listProductDetails() throws ProductException {
		logger.info("\n\n ProductServiceImpl -- listProductDetails() method started \n");
		Iterable<ProductEntity> productEntity = dao.findAll();
		List<Product> listOfProductModels = new ArrayList<>();
		productEntity.forEach(p -> {
			//selecting only those products whose expiry date is valid
			if(p.getExpiryDate().isAfter(LocalDate.now())) {
				
				Product productModel = new Product();
				
				//applying the discount whose expiry is within 6 month from today's date
				long days = ChronoUnit.DAYS.between(LocalDate.now(), p.getExpiryDate());
				if(days <= 180) {
					productModel.setPrice(80*p.getPrice()/100);
				}else {
					productModel.setPrice(p.getPrice());
				}
				productModel.setId(p.getId());
				productModel.setDescription(p.getDescription());
				productModel.setWeight(p.getWeight());
				productModel.setManufacturingDate(p.getManufacturingDate());
				productModel.setExpiryDate(p.getExpiryDate());
				listOfProductModels.add(productModel);
			}
			
		});
		
		listOfProductModels.sort(Comparator.comparing(Product::getExpiryDate).reversed());
		if (listOfProductModels.isEmpty()) {
			logger.info("\n\n No records available  \n");
			throw new ProductException("No records found in the Products details");
		}
			
		logger.info("\n\n ProductServiceImpl -- listProductDetails() method Ended \n");
		return listOfProductModels;
	}

	public boolean isProductsDetailValid(Product productDetails) {

		logger.info("\n\n ProductServiceImpl -- isProductsDetailValid() method started \n");

		if (productDetails.getId() != null && productDetails.getManufacturingDate() != null
				&& productDetails.getManufacturingDate().isBefore(LocalDate.now())) {

			if (productDetails.getExpiryDate() != null) {

				if (productDetails.getManufacturingDate().isBefore(productDetails.getExpiryDate())
						&& productDetails.getExpiryDate().isAfter(LocalDate.now())) {
					Period p = Period.between(productDetails.getManufacturingDate(), productDetails.getExpiryDate());
					if (p.getMonths() >= 3)
						logger.info("\n\n---------------->>>>>>>>>>  Difference between manufacturing & expiry date is minimum 3 month \n");
					logger.info("\n\n!!!!!!!   --------------   Success\n");
					return true;
				} else {
					logger.error("\n\n---------------->>>>>>>>>>  manufacturing date is not before expiry date\n");
					logger.error("\n\n---------------->>>>>>>>>>  Or\n");
					logger.error("\n\n---------------->>>>>>>>>>  expiry date is not after todays date\n");
					return false;
				}

			} else {
				logger.info("\n\n---------------->>>>>>>>>>  Expiry date is not given so going to calulate\n");
				// "manufacturingDate" : "2020-12-01" -- Tue Dec 01 05:30:00 IST 2020
				// need to calculate expiry date as per base on manufacturing date
				LocalDate manufacturingDate = productDetails.getManufacturingDate();
				/*
				 * LocalDate local = manufacturingDate.toInstant()
				 * .atZone(ZoneId.systemDefault()) .toLocalDate();
				 */
				LocalDate expiry = manufacturingDate.plusMonths(12);
				if (expiry.isAfter(LocalDate.now())) {
					Period p = Period.between(manufacturingDate, expiry);
					if (p.getMonths() >= 3)
						logger.info("\n\n---------------->>>>>>>>>>  Difference between manufacturing & expiry date is minimum 3 month \n");
					productDetails.setExpiryDate(expiry);
					logger.info("\n\n!!!!!!!   --------------   Success\n");
					return true;
				} else {
					logger.error("\n\n---------------->>>>>>>>>>  expiry date is not after todays date\n");
					return false;
				}
			}
		}
		logger.info("\n\n---------------->>>>>>>>>>  Manufacturing date or ID is not given or null or NOT BEFORE todays date\n");
		logger.info("\n\n ProductServiceImpl -- isProductsDetailValid() method ended \n");
		return false;
	}

	public Product searchProductByDescription(String productDesc) throws ProductException {

		Optional<ProductEntity> optional = dao.findByDescription(productDesc);
		
		Product productModel2 = new Product();
		if(optional.isPresent()) {
			ProductEntity productEntity = optional.get();
			productModel2.setId(productEntity.getId());
			productModel2.setDescription(productEntity.getDescription());
			productModel2.setPrice(productEntity.getPrice());
			productModel2.setWeight(productEntity.getWeight());
			productModel2.setManufacturingDate(productEntity.getManufacturingDate());
			productModel2.setExpiryDate(productEntity.getExpiryDate());
			return productModel2;
		}else {
			throw new ProductException("Entered product is not available");
		}
	}

	
	public void deleteProductDetails(Integer productId) {
		dao.deleteById(productId);
	}

	public void updateProductDetails(Integer productId, Product productModel) {
		Optional<ProductEntity> proOptional = dao.findById(productId);
		ProductEntity c = proOptional.get();
		//c.setEmailId(emailId);
		if(productModel.getDescription() != null) {
			c.setDescription(productModel.getDescription());
		}if(productModel.getWeight() != null) {
			c.setWeight(productModel.getWeight());
		}if(productModel.getPrice() != null) {
			c.setPrice(productModel.getPrice());
		}if(productModel.getManufacturingDate() != null) {
			c.setManufacturingDate(productModel.getManufacturingDate());
		}if(productModel.getExpiryDate() != null) {
			c.setExpiryDate(productModel.getExpiryDate());
		}	
		ProductEntity save = dao.save(c);
	}

	public boolean isProductExistByID(Integer productId) {
		Optional<ProductEntity> p = dao.findById(productId);
		if(p.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

}