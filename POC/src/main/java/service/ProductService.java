package service;

import exceptions.ProductNotFoundException;
import model.ProductModel;

public interface ProductService {
	
	ProductModel findProduct(int productId) throws ProductNotFoundException;
	public void updatePrice(Integer productId, ProductModel model);

}