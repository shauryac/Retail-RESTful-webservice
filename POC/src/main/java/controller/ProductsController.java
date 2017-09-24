package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.ProductModel;
import service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductServiceImpl productService;

	//GET request to deliver the product data 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProductModel> findProductById(@PathVariable("id") int productId) {
		ProductModel productInfo = productService.findProduct(productId);
		if (productInfo != null) {
		
			return new ResponseEntity<>(productInfo, new HttpHeaders(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//PUT request
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> SaveProductById(@RequestBody ProductModel price, @PathVariable("id") int productId) {
		if (productId != Integer.parseInt(price.getId()))
		{
			return new ResponseEntity<>("Product id mismatch",HttpStatus.BAD_REQUEST);
		}
		productService.updatePrice(productId, price);
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
