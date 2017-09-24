package service;

import model.ProductModel;

public interface ProductService {
	
	ProductModel findProduct(int productId);
	public void updatePrice(Integer productId, ProductModel model);

}