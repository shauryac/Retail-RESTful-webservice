package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {

	@JsonProperty("id")
	String id;
	
	@JsonProperty("name")
	String title;
	
	@JsonProperty("currency_price")
	CurrencyPriceModel currencyPrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public CurrencyPriceModel getCurrencyPrice() {
		return currencyPrice;
	}
	public void setCurrencyPrice(CurrencyPriceModel currencyPrice) {
		this.currencyPrice = currencyPrice;
	}
	
}
