package com.bank.app.accounts;

public class YatirimHesabi extends BankaHesabi {
	private String HesapTürü = "Yatirim";
	
	
	
	public YatirimHesabi(double bakiye){
		super(bakiye);
	}
	
	public void paraEkle(double miktar) {
		super.setBakiye(super.getBakiye() + miktar);
		System.out.println("Para Yatırıldı.");
	}
	
	public void paraCek(double miktar) {
		if(miktar < super.getBakiye()) {
			System.out.println("Yeterli Para Yok.");
		}
		else {
			super.setBakiye(super.getBakiye() - miktar);
		}
	}
	
	
	public String toString() {
		return "Hesap Türü :" + HesapTürü + "\nBakiye" + super.getBakiye() + "IBAN :" + super.getIban() + "\n \n";
	}
	
	
	
}
