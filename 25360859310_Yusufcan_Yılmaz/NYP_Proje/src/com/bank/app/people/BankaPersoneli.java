package com.bank.app.people;
import java.util.ArrayList;
import java.util.Random;

public class BankaPersoneli extends Kisi {
	private String PersonalID = "BP"; //ID'nin başını BP ile başlatarak banka personeli olduğunun belli olmasını istedim.
	private int sifre;
	private ArrayList<Musteri> Musteriler;
	
	public BankaPersoneli(String ad,String soyad,String email,int telefonNumarasi) {
		super(ad,soyad,email,telefonNumarasi);
		this.PersonalID = idGenerator();
		this.Musteriler = new ArrayList<>();
		sifre = telefonNumarasi % 10000;  //Şifreyi telefon numarasının son 4 hanesi yaptım.
		System.out.println("Banka Personeli oluşturuldu. ID :"+ PersonalID);
	}
	
	private String idGenerator() {
		Random random = new Random();
		
		PersonalID+= (random.nextInt(9)+1);
		for(int i = 0 ; i < 7 ; i++) {
			PersonalID += (random.nextInt(10));
		}
		return PersonalID;
	}
	
	public boolean sifreKontrol(int sifre) {
        return this.sifre == sifre;
    }
	
	
	
	public ArrayList<Musteri> getMusteriler() {
		return Musteriler;
	}


	public String getPersonalID() {
		return PersonalID;
	}

	@Override
	public String toString() {
		return super.toString()+"\nPersonel ID :"+PersonalID;
	}
	
	
	
}
