package com.bank.app.cards;

import java.util.Random;

public class KrediKarti {
	private String KartNumarasi = "";
	private double limit;
	private double guncelBorc;
	private double kullanilabilirLimit;
	
	
	public KrediKarti(double limit,double guncelBorc) {
		this.limit = limit;
		this.guncelBorc = guncelBorc;
		KartNumarasi = randomKartNo();
		kullanılabilirLimitGüncelleme();
	}
	
	//Random ile kart numarası oluşturdum.
	public String randomKartNo() {
		Random random = new Random();
		KartNumarasi += (random.nextInt(9)+1);
		for(int i = 0 ; i < 15 ; i++) {
			KartNumarasi += (random.nextInt(10));
		}
		return KartNumarasi;
	}
	
	//Guncel borç değiştikten sonra bu fonksiyonu kullanarak kullanılabilir limit güncelliyorum.
	private void kullanılabilirLimitGüncelleme() {
        this.kullanilabilirLimit = this.limit - this.guncelBorc;
    }
	
	public double getGuncelBorc() {
		return guncelBorc;
	}


	public void setGuncelBorc(double guncelBorc) {
		this.guncelBorc = guncelBorc;
		kullanılabilirLimitGüncelleme();
	}


	public String getKartNumarasi() {
		return KartNumarasi;
	}


	public double getLimit() {
		return limit;
	}


	public double getKullanilabilirLimit() {
		return kullanilabilirLimit;
	}


	public String toString() {
		return "Kart Numarası :" + KartNumarasi + "\nLimit :" + limit + "\nGüncel Borç :" + guncelBorc + "\nKullanılabilir Limit :" + kullanilabilirLimit + "\n \n" ;
	}
	
	
	
	
	
	
	
}
