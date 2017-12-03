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

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import exceptions.ProductIdMismatchException;
import exceptions.ProductNotFoundException;
import model.ProductModel;
import service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductService productService;

	// GET request to deliver the product data based on product Id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProductModel> findProductById(@PathVariable("id") int productId)
			throws ProductNotFoundException {
		ProductModel productInfo = productService.findProduct(productId);
		return new ResponseEntity<>(productInfo, new HttpHeaders(), HttpStatus.OK);
	}

	// PUT request
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> SaveProductById(@RequestBody ProductModel productInfo,
			@PathVariable("id") int productId) throws InvalidFormatException {

		if (productId != productInfo.getId()) {
			throw new ProductIdMismatchException("Product Id mismatch from URI and body");
		}
		productService.updatePrice(productId, productInfo);
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
