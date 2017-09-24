package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import model.CurrencyPriceModel;
import model.ProductModel;

@Repository
public class UpdateRepository {
	@Autowired
	private MongoOperations operations;

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
