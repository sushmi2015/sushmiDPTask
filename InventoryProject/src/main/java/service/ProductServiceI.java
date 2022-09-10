package service;

import java.util.List;
import java.util.Map;

import entity.ProductEntity;
import exception.ProductException;
import model.Product;

public interface ProductServiceI {


	public ProductEntity addProductDetails(Product productModel);

	public boolean isProductsDetailValid(Product productDetails);

	public List<Product> listProductDetails() throws ProductException;

	public boolean isProductExist(String productDesc);

	public Product searchProductByDescription(String productDesc) throws ProductException;

	public void deleteProductDetails(Integer productDesc);

	public boolean isProductExistByID(Integer productId);

	public void updateProductDetails(Integer productId, Product productModel);

}