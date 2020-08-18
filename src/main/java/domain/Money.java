package domain;

import java.io.Serializable;

public class Money implements Serializable{
	private Currency currency;
	private Float 	amount;
	
	private CurrencyProvider currencyProvider = new CurrencyProvider();
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	public Money() {
		this.currency = CurrencyProvider.getInstance().getCurrency("EUR"); 
		this.amount = (float) 0;
	}
	
	public Money(String currencyCode, Float amount) {
		this.currency = CurrencyProvider.getInstance().getCurrency(currencyCode); 
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Money [currency=" + currency.toString() + ", amount=" + amount + "]";
	}
	
	 public Currency toCurrency(String code){
		Currency c = currencyProvider.getCurrency(code);
		Currency newCurrency = new Currency(code, currency.getRate()*amount/c.getRate());
	 	return newCurrency;
	 }
}
