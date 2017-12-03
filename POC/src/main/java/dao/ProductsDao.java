package dao;

import model.ProductModel;

public interface ProductsDao {
	
	// find product name based on id from the redsky api
	String findProductNameById(int productId);
	
	// update currency details in the database
	void updatePrice(Integer productId, ProductModel model);

}
