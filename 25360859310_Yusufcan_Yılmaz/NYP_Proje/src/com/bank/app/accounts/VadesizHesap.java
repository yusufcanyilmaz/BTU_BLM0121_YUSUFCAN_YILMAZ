package com.bank.app.accounts;

import java.util.Random; 

import com.bank.app.cards.KrediKarti;

public class VadesizHesap extends BankaHesabi {
	private String HesapTürü = "Vadesiz";
	
	
	public VadesizHesap(double bakiye) {
		super(bakiye);

	}
	
	
	public void paraTransferi(BankaHesabi alicihesap,BankaHesabi gonderenhesap,double miktar) {
		if(miktar > gonderenhesap.getBakiye()) {
			System.out.println("Para transferi için yeterli bakiye yok.");
		}
		else {
			gonderenhesap.setBakiye(gonderenhesap.getBakiye() - miktar);
			alicihesap.setBakiye(alicihesap.getBakiye() + miktar);
			System.out.println("Para Transferi Başarılı.");
			
		}
	}
	
	public void krediKartiBorcOdeme(KrediKarti kart,double miktar) {
		if(kart.getGuncelBorc() == 0) {
			System.out.println("Güncel borcunuz bulunmamaktadır.");
		}
		else if(miktar < super.getBakiye()) {
			System.out.println("Hesapta yeterli bakiye bulunamadı!");
		}
		else {
			if(miktar >= kart.getGuncelBorc()) {
				super.setBakiye(super.getBakiye() - miktar);
				kart.setGuncelBorc(kart.getGuncelBorc() - miktar);
			}
		}
	}
	
	
	public String toString() {
		return "Hesap Türü :" + HesapTürü + "\nBakiye" + super.getBakiye() + "\nIBAN :" + super.getIban()+ "\n \n";
	} 
}
