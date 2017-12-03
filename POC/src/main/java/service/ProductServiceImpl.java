package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PriceRepository;
import dao.ProductsDao;
import exceptions.ProductNotFoundException;
import model.CurrencyPriceModel;
import model.ProductModel;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private ProductsDao productDao;

	public ProductModel findProduct(int productId) throws ProductNotFoundException  {
		// get currency details from the database
		CurrencyPriceModel currencyModel = priceRepository.findByProductId(productId);
		
		// if there is currency details for the product id
		if (currencyModel != null) {	
			
			// get product name from redsky target api
			String productName = productDao.findProductNameById(productId);
			
			ProductModel productModel = new ProductModel();
			productModel.setCurrencyPrice(currencyModel);
			productModel.setId(productId);
			productModel.setName(productName);
			return productModel;
		}
		throw new ProductNotFoundException("Product not found with product id " + productId);

	}

	public void updatePrice(Integer productId, ProductModel model) {
		// update currency details in the database 
		productDao.updatePrice(productId, model);
	}
}
