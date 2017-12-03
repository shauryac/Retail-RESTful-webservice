package dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.CurrencyPriceModel;
import model.ProductModel;

@Repository
public class ProductsDaoImpl implements ProductsDao {
	
	@Autowired
	private MongoOperations operations;

	public String findProductNameById(int productId) {

		String productName = null;
		
		// Build URL
		String url = "http://redsky.target.com/v1/pdp/tcin/" + productId
				+ "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

		RestTemplate restTemplate = new RestTemplate();
		// Call service
		String result = null;
		result = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootArray = mapper.readTree(result);
			for (JsonNode root : rootArray) {
				JsonNode itemNode = root.path("item");
				JsonNode productDescription = itemNode.path("product_description");
				productName = productDescription.path("title").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return productName;
	}

	public void updatePrice(Integer productId, ProductModel model) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(productId));

		CurrencyPriceModel userTest = operations.findOne(query, CurrencyPriceModel.class);
		if (userTest != null) {
			userTest.setValue(model.getCurrencyPrice().getValue());	
			operations.save(userTest);
		}
	}
}
