package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import model.CurrencyPriceModel;

@Repository
public interface PriceRepository extends MongoRepository<CurrencyPriceModel, Integer> {

	// get currency details from the database based on productId
	@Query(value = "{productId: ?0}")
	CurrencyPriceModel findByProductId(Integer productId);

}