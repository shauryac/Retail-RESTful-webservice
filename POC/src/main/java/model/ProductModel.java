package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {

	
	@JsonProperty("id")
	int id;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("current_price")
	CurrencyPriceModel currencyPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CurrencyPriceModel getCurrencyPrice() {
		return currencyPrice;
	}
	public void setCurrencyPrice(CurrencyPriceModel currencyPrice) {
		this.currencyPrice = currencyPrice;
	}
	
}
