package products;

import static com.jayway.restassured.RestAssured.*;

import org.junit.Test;

public class ProductsComponentTest {

	@Test
	public void getProductById() {
		given().contentType("application/json").when().get("http://localhost:8080/products/13860428").then().assertThat()
				.statusCode(200);
	}
	
	@Test
	public void getProductByInvalidId() {
		given().contentType("application/json").when().get("http://localhost:8080/products/0").then().assertThat()
				.statusCode(404);
	}
	
	@Test
	public void UpdateProductPrice() {
		String body = "{\"id\": \"13860428\",\"name\": \"The Big Lebowski (Blu-ray)\",\"currency_price\": {\"currencyCode\": \"USD\",\"value\": 134}}";
		given().body(body).contentType("application/json").when().put("http://localhost:8080/products/13860428").then().assertThat()
				.statusCode(204);
	}
}
