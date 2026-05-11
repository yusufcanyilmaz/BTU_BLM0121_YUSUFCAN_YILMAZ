package com.bank.app.people;
import java.util.ArrayList;
import com.bank.app.cards.KrediKarti;
import java.util.Random;

import com.bank.app.accounts.*;
import com.bank.app.cards.*;

public class Musteri extends Kisi{
	private String MusteriNumarasi = "M"; //Müşteri belli olsun diye başına m harfi koydum.
	private ArrayList<BankaHesabi> hesaplar;
	private ArrayList<KrediKarti> krediKartlari;
	
	
	public Musteri(String ad,String soyad,String email,int telefonNumarasi) {
		super(ad,soyad,email,telefonNumarasi);
		MusteriNumarasi = idGenerator2();
		hesaplar = new ArrayList<>();
		krediKartlari = new ArrayList<>();
	}
	
	private String idGenerator2() {
		Random random = new Random();

		for(int i = 0 ; i < 8 ; i++) {
			MusteriNumarasi += (random.nextInt(9));
		}
		return MusteriNumarasi;
	}
	
	public void hesapEkle(String hesapTuru) {
		BankaHesabi yenihesap = null;
		if(hesapTuru.equals("Vadesiz")) {
			yenihesap = new VadesizHesap(0.0);
			
		}
		else if (hesapTuru.equals("Yatirim")) {
			yenihesap = new YatirimHesabi(0.0);
		}
		hesaplar.add(yenihesap);
	}
	
	public void krediKartiEkle(double limit) {
		krediKartlari.add(new KrediKarti(limit,0.0));
	}
	
	public void hesapSil(BankaHesabi hesap) {
		//Bu metotda hesap silmeden önce hesapta bakiye olma durumu incelenir ve bakiye varsa uyarı mesajı gönderilir.
		if(hesap.getBakiye() == 0) {
			hesaplar.remove(hesap);
			System.out.println("Hesap Silindi.");
		}
		else {
			System.out.println("Hesaptaki bakiyeyi başka yere aktarın veya çekin!");
		}
	}
	
	public void krediKartiSil(KrediKarti kart) {
		//Bu metotda kredi kartı silmeden önce kartın borcu yoksa silinir kartın borcu var ise uyarı mesajı gönderilir.
		if(kart.getGuncelBorc() == 0) {
			krediKartlari.remove(kart);
			System.out.println("Kredi kartı silindi.");
		}
		else {
			System.out.println("Lütfen öncelikle borcunuzu ödeyiniz!");
		}
	}
	
	
	public String getMusteriNumarasi() {
		return MusteriNumarasi;
	}

	public ArrayList<BankaHesabi> getHesaplar() {
		return hesaplar;
	}

	public ArrayList<KrediKarti> getKrediKartlari() {
		return krediKartlari;
	}

	public String toString() {
		return super.toString()+"\nMusteri Numarası :"+MusteriNumarasi;
	}
	
	
	
	
	
	
	
	
	
}
