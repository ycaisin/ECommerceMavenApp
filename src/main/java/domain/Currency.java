package domain;

import java.io.Serializable;

public class Currency implements Serializable{
    public static Currency baseCurrency = new Currency (
            "EUR",
             1.00         
        ); 
        private String code;       // e.g. "EUR"
        private double rate;
        
    	public Currency() {
		}
    	
		public Currency(String code, double rate) {
			this.code = code;
			this.rate = rate;
		}
		
		public static Currency getBaseCurrency() {
			return baseCurrency;
		}
		public static void setBaseCurrency(Currency baseCurrency) {
			Currency.baseCurrency = baseCurrency;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public double getRate() {
			return rate;
		}
		public void setRate(double rate) {
			this.rate = rate;
		}  
		@Override
		public String toString() {
			return "Currency [code=" + code + ", rate=" + rate + "]";
		}


        
}
