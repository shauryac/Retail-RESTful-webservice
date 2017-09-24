package dao;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProductsDaoImpl implements ProductsDao {

	public HashMap<Integer,String> findProductIdAndName(int productId) {

		int id = 0;
		String productName = null;
		JsonNode itemNode = null;
		JsonNode productDescription = null;
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		// Build URL
		String url = "http://redsky.target.com/v1/pdp/tcin/" + productId
				+ "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

		RestTemplate restTemplate = new RestTemplate();
		// Call service
		String result = null;
		result = restTemplate.getForObject(url.toString(), String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootArray = mapper.readTree(result);
			for (JsonNode root : rootArray) {
				itemNode = root.path("item");
				id = itemNode.path("tcin").asInt();
				productDescription = itemNode.path("product_description");
				productName = productDescription.path("title").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put(id,productName);
		return map;
	}
}
