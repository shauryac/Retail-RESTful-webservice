package service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductsDao;
import model.CurrencyPriceModel;
import model.ProductModel;
import repository.PriceRepository;
import repository.UpdateRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private UpdateRepository updateRepository;

	@Autowired
	private ProductsDao productsname;

	public ProductModel findProduct(int productId) {
		CurrencyPriceModel currencyModel = priceRepository.findByProductId(productId);
		
		if (currencyModel != null) {
			ProductModel productModel = new ProductModel();
			HashMap<Integer, String> map = productsname.findProductIdAndName(productId);
			productModel.setCurrencyPrice(currencyModel);
			if (map.containsKey(productId)) {
				Object value = map.get(productId);
				productModel.setId(String.valueOf(productId));
				productModel.setTitle(value.toString());
			}
			return productModel;
		}
		return null;
		
	}

	public void updatePrice(Integer productId, ProductModel model) {
		updateRepository.updatePrice(productId, model);
	}
}
