package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javafaker.Faker;

public class CurrencyProvider implements Serializable{
	private static CurrencyProvider instance;
	private Map<String,Currency> currencies; // дает доступ по ключе
	
	public CurrencyProvider(){
		this.currencies = new HashMap<String, Currency>(); 
		currencies.put("EUR", new Currency("EUR", 1));
		currencies.put("USD", new Currency("USD",0.8737));
		currencies.put("MDL", new Currency("MDL",0.0512));
		currencies.put("RUB", new Currency("RUB",0.0114)); 
		currencies.put("RON", new Currency("RON",0.2065));
	}
	
	public static CurrencyProvider getInstance(){
		if(instance == null)
			synchronized (CurrencyProvider.class) {
				if(instance == null)
					instance = new CurrencyProvider();
			}
		return instance;
	}
	
	public Currency getCurrency(String code){
		return currencies.get(code);   
	}
	
	public String generateFakeCurrencyCode(){
		Faker faker = new Faker();
		String code = faker.currency().code();
		while(currencies.get(code) == null)
			code = faker.currency().code();
		return code;
	}
}
