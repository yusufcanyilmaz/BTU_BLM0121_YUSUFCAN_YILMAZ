package com.bank.app.service;

import com.bank.app.people.BankaPersoneli;

public class BankaService {
	
	// Bu metodu personel girişi almadan bütün işlerin yapılmasını engellemek için yazdım.
	public static boolean personelGirisYap(BankaPersoneli personel, int sifre,String email) {	
		
		if (personel.sifreKontrol(sifre) && personel.getEmail().equals(email)) {
            System.out.println("Başarılı Giriş.Hoş geldiniz, " + personel.getAd());
            return true;
        } 
		else {
            System.out.println("Hatalı Giriş.Giriş Yapılamadı!");
            return false;
        }
    }
	
	
	  
}
