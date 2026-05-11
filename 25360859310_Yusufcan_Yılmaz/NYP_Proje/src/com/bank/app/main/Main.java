package com.bank.app.main;

import com.bank.app.accounts.*;  
import com.bank.app.cards.*;
import com.bank.app.people.*;
import com.bank.app.service.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        //Banka Personeli nesnesi oluşturdum çünkü bir personel girişi alacağız.
        BankaPersoneli personel = new BankaPersoneli("Yusufcan", "Yılmaz", "yusuf@gmail.com", 1234567891);
        
        // Bu döngüde email ve şifre girişi alıp doğru mu değil mi olduğunu kontrol ediyoruz.
        boolean girisBasarili = false;
        while (!girisBasarili) {
            System.out.println("Email adresinizi girin :");
            String girilenEmail = input.next(); 
            System.out.print("Personel sifrenizi girin :");
            int girilenSifre = input.nextInt();
            
            if (BankaService.personelGirisYap(personel, girilenSifre, girilenEmail)) {
                girisBasarili = true;
            }
        }
        
        Musteri testMusterisi = null;
        boolean devam1 = true;
        
        //Giriş başarılıysa bu döngüye giriyor ve müşteri seçimine gidiyoruz.
        //Eğer müşteri yoksa direk menü seçimine gider.

        while (devam1) {
            
            Musteri secilenMusteri = null;
            
            if (!personel.getMusteriler().isEmpty()) {
                System.out.println("\n--- İŞLEM YAPILACAK MÜŞTERİYİ SEÇİN ---");
                
                for (int i = 0; i < personel.getMusteriler().size(); i++) {
                    Musteri m = personel.getMusteriler().get(i);
                    System.out.println(i + " - " + m.getAd() + " " + m.getSoyad() + " (No: " + m.getMusteriNumarasi() + ")");
                }
                System.out.println(personel.getMusteriler().size() + " - [Yeni Musteri Olusturma Menusune Git]");
                System.out.print("Seçiminiz : ");
                     
                int mSecim = input.nextInt();
                if (mSecim >= 0 && mSecim < personel.getMusteriler().size()) {
                    secilenMusteri = personel.getMusteriler().get(mSecim);
                    testMusterisi = secilenMusteri;
                }
            } 
            else {
                System.out.println("\n(Sistemde kayıtlı müşteri bulunmadığı için direkt menüye geçiliyor.)");
            } 
            
            
            //Menü seçimi 
            
            System.out.println("\n--- BANKA İŞLEM MENÜSÜ ---");
            System.out.println("1 - Müşteri Oluştur");
            System.out.println("2 - Müşteri Adına Hesap Aç");
            System.out.println("3 - Hesaba Para Yatır");
            System.out.println("4 - Hesaplar Arası Para Transferi");
            System.out.println("5 - Kredi Kartı Tanımla");
            System.out.println("6 - Kredi Kartı Borcu Öde");
            System.out.println("7 - Hesap Sil");
            System.out.println("8 - Kredi Kartı Sil");
            System.out.println("9 - Müşteri Bilgilerini Göster");
            System.out.println("0 - Güvenli Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = input.nextInt();

            //Menüdeki seçeneklerden bir sayı alıyoruz.
            //Bu sayıyı da secime atayıp switch case içine giriyoruz.
            
            switch (secim) {
                case 1:
                	// Müşteri oluşturma işlemi
                	
                    System.out.println("İsim Girin :");
                    String isim = input.next();
                    
                    System.out.println("Soyisim Girin :");
                    String soyisim = input.next();
                    
                    System.out.println("Email Girin :");
                    String email = input.next();
                    
                    System.out.println("Telefon Numarası Girin :");
                    int telNo = input.nextInt();
                    
                    testMusterisi = new Musteri(isim, soyisim, email, telNo);
                    personel.getMusteriler().add(testMusterisi);
                    System.out.println("Müşteri başarıyla oluşturuldu. No: " + testMusterisi.getMusteriNumarasi());
                    break;

                case 2:
                	// Hesap açma işlemi
                	
                    if (secilenMusteri == null) {
                        System.out.println("UYARI: İşlem yapabilmek için listeden bir müşteri seçmelisiniz!");
                    } 
                    else {
                        System.out.println("\nAçılacak Hesap Türü:");
                        System.out.println("1 - Vadesiz Hesap");
                        System.out.println("2 - Yatırım Hesabı");
                        System.out.print("Seçim: ");
                        int hTur = input.nextInt();
                        
                        if (hTur == 1) {
                            secilenMusteri.hesapEkle("Vadesiz");
                            System.out.println("Vadesiz hesap açıldı.");
                        }
                        else if (hTur == 2) {
                            secilenMusteri.hesapEkle("Yatirim");
                            System.out.println("Yatırım hesabı açıldı.");
                        } 
                        else {
                            System.out.println("Geçersiz tür seçimi!");
                        }
                    }
                    break;

                case 3:
                	//Para yatırma işlemi
                	
                    if (secilenMusteri == null) {
                        System.out.println("UYARI: Önce müşteri seçmelisiniz!");
                    } else if (secilenMusteri.getHesaplar().isEmpty()) {
                        System.out.println("UYARI: Müşterinin hesabı bulunmamaktadır!");
                    } else {
                        System.out.println("\n--- Para Yatırılacak Hesabı Seçin ---");
                        for (int i = 0; i < secilenMusteri.getHesaplar().size(); i++) {
                            BankaHesabi h = secilenMusteri.getHesaplar().get(i);
                            String tur = (h instanceof VadesizHesap) ? "Vadesiz" : "Yatirim";
                            System.out.println(i + " - " + tur + " | IBAN: " + h.getIban() + " | Bakiye: " + h.getBakiye());
                        }
                        System.out.print("Hesap Seçimi: ");
                        int hIdx = input.nextInt();

                        if (hIdx >= 0 && hIdx < secilenMusteri.getHesaplar().size()) {
                            System.out.print("Yatırılacak Miktar: ");
                            double miktar = input.nextDouble();
                            BankaHesabi hedefH = secilenMusteri.getHesaplar().get(hIdx);

                            if (hedefH instanceof YatirimHesabi) {
                                ((YatirimHesabi) hedefH).paraEkle(miktar);
                            } else {
                                hedefH.setBakiye(hedefH.getBakiye() + miktar);
                                System.out.println("İşlem başarılı. Yeni bakiye: " + hedefH.getBakiye());
                            }
                        } else {
                            System.out.println("Geçersiz hesap seçimi!");
                        }
                    }
                    break;

                case 4:
                	//Transfer işlemi
                	
                    if (secilenMusteri == null || secilenMusteri.getHesaplar().size() < 2) {
                        System.out.println("UYARI: Transfer için müşterinin en az 2 hesabı olmalıdır!");
                    } 
                    else {
                        System.out.println("\nTransfer İşlemi :");
                        for (int i = 0; i < secilenMusteri.getHesaplar().size(); i++) {
                            System.out.println(i + " - " + secilenMusteri.getHesaplar().get(i).getIban() + " | Bakiye: " + secilenMusteri.getHesaplar().get(i).getBakiye());
                        }
                        System.out.print("Gönderen Hesap : ");
                        int gonderenIdx = input.nextInt();
                        System.out.print("Alıcı Hesap : ");
                        int aliciIdx = input.nextInt();
                        System.out.print("Transfer Miktarı: ");
                        double tMiktar = input.nextDouble();

                        BankaHesabi gonderen = secilenMusteri.getHesaplar().get(gonderenIdx);
                        BankaHesabi alici = secilenMusteri.getHesaplar().get(aliciIdx);
                        
                        ((VadesizHesap) gonderen).paraTransferi(alici,gonderen,tMiktar);
                    }
                    break;

                case 5:
                	//Kredi kartı tanımlama
                	
                    if (secilenMusteri == null) { System.out.println("Müşteri seçin!"); break; }
                    System.out.print("Kart Limiti: ");
                    double limit = input.nextDouble();
                    
                    secilenMusteri.krediKartiEkle(limit);
                    System.out.println("Kredi kartı tanımlandı.");
                    break;

                case 6:
                	//Kredi kartı borç ödeme işlemi
                	
                    if (secilenMusteri == null || secilenMusteri.getKrediKartlari().isEmpty() || secilenMusteri.getHesaplar().isEmpty()) {
                        System.out.println("Hata: İşlem için müşterinin hem hesabı hem de kredi kartı olmalıdır!");
                        break;
                    }
                   
                    System.out.println("\n--- Borcu Ödenecek Kartı Seçin ---");
                    for (int i = 0; i < secilenMusteri.getKrediKartlari().size(); i++) {
                        KrediKarti k = secilenMusteri.getKrediKartlari().get(i);
                        System.out.println(i + " - No: " + k.getKartNumarasi() + " | Güncel Borç: " + k.getGuncelBorc());
                    }
                    System.out.print("Seçiminiz: ");
                    int kIdx = input.nextInt();

                    System.out.println("\n--- Ödemenin Yapılacağı Vadesiz Hesabı Seçin ---");
                    for (int i = 0; i < secilenMusteri.getHesaplar().size(); i++) {
                        if (secilenMusteri.getHesaplar().get(i) instanceof VadesizHesap) {
                            VadesizHesap vh = (VadesizHesap) secilenMusteri.getHesaplar().get(i);
                            System.out.println(i + " - IBAN: " + vh.getIban() + " | Bakiye: " + vh.getBakiye());
                        }
                    }
                    System.out.print("Seçiminiz: ");
                    int hIdx = input.nextInt();

                    System.out.print("Ödemek istediğiniz miktar: ");
                    double odemeMiktari = input.nextDouble();

                    VadesizHesap secilenVH = (VadesizHesap) secilenMusteri.getHesaplar().get(hIdx);
                    KrediKarti secilenKart = secilenMusteri.getKrediKartlari().get(kIdx);
                    secilenVH.krediKartiBorcOdeme(secilenKart, odemeMiktari);
                    break;

                case 7:
                	// Hesap silme İşlemi 
                	
                	if (secilenMusteri == null || secilenMusteri.getHesaplar().isEmpty()) {
                        System.out.println("UYARI: Silinecek hesap bulunamadı!");
                        break;
                    }

                    System.out.println("\n--- Silinecek Hesabı Seçin ---");
                    for (int i = 0; i < secilenMusteri.getHesaplar().size(); i++) {
                        BankaHesabi h = secilenMusteri.getHesaplar().get(i); 
                        String tur = (h instanceof VadesizHesap) ? "Vadesiz" : "Yatırım";
                        System.out.println(i + " - " + tur + " (IBAN: " + h.getIban() + ") | Bakiye: " + h.getBakiye());
                    }
                    
                    System.out.print("Silmek istediğiniz hesabın numarasını girin: ");
                    int silHIdx = input.nextInt();
 
                    if (silHIdx >= 0 && silHIdx < secilenMusteri.getHesaplar().size()) {
                        secilenMusteri.hesapSil(secilenMusteri.getHesaplar().get(silHIdx));
                    } else {
                        System.out.println("Hata: Geçersiz bir numara girdiniz!");
                    }
                    break;
                    
                case 8: 
                	//Kredi kartı silme işlemi                	
                	
                	if (secilenMusteri == null || secilenMusteri.getKrediKartlari().isEmpty()) {
                        System.out.println("UYARI: Silinecek kredi kartı bulunamadı!");
                        break;
                    }
                    
                    System.out.println("\n--- Silinecek Kredi Kartını Seçin ---");
                    for (int i = 0; i < secilenMusteri.getKrediKartlari().size(); i++) {
                        KrediKarti kk = secilenMusteri.getKrediKartlari().get(i);
                        System.out.println(i + " - No: " + kk.getKartNumarasi() + " | Güncel Borç: " + kk.getGuncelBorc());
                    }
                    System.out.print("Seçiminiz: ");
                    int silKIdx = input.nextInt();

                    if (silKIdx >= 0 && silKIdx < secilenMusteri.getKrediKartlari().size()) {
                        secilenMusteri.krediKartiSil(secilenMusteri.getKrediKartlari().get(silKIdx));
                        System.out.println("Kredi Kartı başarıyla silindi.");
                    } else {
                        System.out.println("Geçersiz Secim!");
                    }
                    break;
                    
                case 9:
                	//Müşteri bilgileri gösterme işlemi
                    if (secilenMusteri == null) {
                        System.out.println("UYARI: Önce bir müşteri seçmelisiniz!");
                    } else {
                        System.out.println("\n===== MÜŞTERİ DETAYLI BİLGİLERİ =====");
                        System.out.println("Müşteri No      : " + secilenMusteri.getMusteriNumarasi());
                        System.out.println("Ad Soyad        : " + secilenMusteri.getAd() + " " + secilenMusteri.getSoyad());
                        System.out.println("E-posta         : " + secilenMusteri.getEmail());
                        System.out.println("Telefon         : " + secilenMusteri.getTelefonNumarasi());
                        
                        System.out.println("\n--- Kayıtlı Hesaplar ---");
                        if (secilenMusteri.getHesaplar().isEmpty()) {
                            System.out.println("Kayıtlı hesap bulunamadı.");
                        } else {
                            for (BankaHesabi hItem : secilenMusteri.getHesaplar()) {
                                String tur = (hItem instanceof VadesizHesap) ? "Vadesiz" : "Yatırım";
                                System.out.println("- [" + tur + "] IBAN: " + hItem.getIban() + " | Bakiye: " + hItem.getBakiye() + " TL");
                            }
                        }
                        
                        System.out.println("\n--- Kayıtlı Kredi Kartları ---");
                        if (secilenMusteri.getKrediKartlari().isEmpty()) {
                            System.out.println("Kayıtlı kredi kartı bulunamadı.");
                        } else {
                            for (KrediKarti kItem : secilenMusteri.getKrediKartlari()) {
                                System.out.println("- No: " + kItem.getKartNumarasi() + " | Limit: " + kItem.getLimit() + " | Borç: " + kItem.getGuncelBorc());
                            }
                        }
                        
                    }
                    break;
                    
                case 0:
                	//Programı kapatmak için 0 basıyoruz.
                	
                    devam1 = false;
                    System.out.println("Banka sisteminden çıkış yapılıyor. İyi günler!");
                    break;

                default:
                	//Sayılar dışı bir şey girildiğinde bu case'e gelir ve uyarı mesajı gelip menü başa döner.
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
        input.close();
    } 

   
    public boolean personelGirisYap(BankaPersoneli personel, int girilenSifre) {
        return false;
    }
}