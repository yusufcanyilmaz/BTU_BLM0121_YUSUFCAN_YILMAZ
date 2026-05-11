package com.bank.app.accounts;
import java.util.Random;

public class BankaHesabi {
	private String iban = "TR";
	private double bakiye;
	
	
	public BankaHesabi(double bakiye) {
		this.bakiye = bakiye;
		iban = ibanGenerator();
	}
	
	//İbanı random ile bir metot yapıp oluşturdum.
	public String ibanGenerator() {
		Random random = new Random();
		iban += (random.nextInt(9) +1);
		for(int i = 0 ; i < 23 ; i++) {
			iban += (random.nextInt(10));
		}
		return iban;
	}
	
	public double getBakiye() {
		return bakiye;
	}

	public void setBakiye(double bakiye) {
		this.bakiye = bakiye;
	}

	public String getIban() {
		return iban;
	}

	public String toString() {
		return "IBAN :" + iban + "\nBakiye :" + bakiye + "\n";
	}

	

	
}
