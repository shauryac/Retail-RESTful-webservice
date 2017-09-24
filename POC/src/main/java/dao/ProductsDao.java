package dao;

import java.util.HashMap;

public interface ProductsDao {
	HashMap<Integer,String> findProductIdAndName(int productId);

}
