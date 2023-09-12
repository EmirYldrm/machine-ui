package Model;

import java.io.Serializable;
import java.util.Date;

import Model.Motor.KalipMotor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Process implements Serializable{

	private String isim;
	
	private Date tarih;

	private int parcaSayisi = 0;
	private transient IntegerProperty parcaSayisiProperty = new SimpleIntegerProperty();
	private transient IntegerProperty hedefSayiProperty =  new SimpleIntegerProperty();
	





	private int hedefSayi;
	private int parcaDusurmeSayisi;
	private int beklemeSuresi;

	private long helezonAdim; // buna gerek kalmayacak! Çünkü tek bir yonde enjeksiyonun döndüğü kadar
								// dönecek.
	private long enjeksiyonAdim;
	private long kalipAdim;
	private long pinUzunluk;
	private long retruction;

	private float helezonHiz;
	private float enjeksiyonHiz;
	private float dolumHiz;
	private float kalipHiz;
	private float adetUcreti;
	private float kazanilanUcret;
	private float enjeksiyonHelezonFactor;
	private float parcaHacim;

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	

	public void setParcaSayisi(int parcaSayisi) {
		this.parcaSayisi = parcaSayisi;
	}
	
	// parça sayisi bind için getter setter
	public int getParcaSayisi() {
		return parcaSayisiProperty.get();
	}

	public void setParcaSayisiProperty(int sayi) {
		this.parcaSayisiProperty.set(sayi);
		this.parcaSayisi = sayi;
	}
	
	public IntegerProperty getParcaSayisiProperty() {
		return parcaSayisiProperty;
	}

	public void setParcaSayisiProperty(IntegerProperty parcaSayisiProperty) {
		this.parcaSayisiProperty = parcaSayisiProperty;
	}


	public IntegerProperty getHedefSayiProperty() {
		return hedefSayiProperty;
	}

	public void setHedefSayiProperty(IntegerProperty hedefSayiProperty) {
		this.hedefSayiProperty = hedefSayiProperty;
	}
	
	public int getHedefSayi() {
		return this.hedefSayi;
		
	}

	public void setHedefSayi(int hedefSayi) {
		this.hedefSayi = hedefSayi;
		this.hedefSayiProperty.set(hedefSayi);
	}

	public int getParcaDusurmeSayisi() {
		return parcaDusurmeSayisi;
	}

	public void setParcaDusurmeSayisi(int parcaDusurmeSayisi) {
		this.parcaDusurmeSayisi = parcaDusurmeSayisi;
	}

	public int getBeklemeSuresi() {
		return beklemeSuresi;
	}

	public void setBeklemeSuresi(int beklemeSuresi) {
		this.beklemeSuresi = beklemeSuresi;
	}

	public long getHelezonAdim() {
		return helezonAdim;
	}

	public void setHelezonAdim(long helezonAdim) {
		
		this.helezonAdim = helezonAdim;
	}

	public long getEnjeksiyonAdim() {
		return enjeksiyonAdim;
	}

	public void setEnjeksiyonAdim(long enjeksiyonAdim) {
		this.enjeksiyonAdim = enjeksiyonAdim;
	}

	public long getKalipAdim() {
		return kalipAdim;
	}

	public void setKalipAdim(long kalipAdim) {
		
		
		this.kalipAdim = kalipAdim;
		
	}

	public long getPinUzunluk() {
		return pinUzunluk;
	}

	public void setPinUzunluk(long pinUzunluk) {
		this.pinUzunluk = pinUzunluk;
	}

	public long getRetruction() {
		return retruction;
	}

	public void setRetruction(long retruction) {
		this.retruction = retruction;
	}

	public float getHelezonHiz() {
		return helezonHiz;
	}

	public void setHelezonHiz(float helezonHiz) {
		this.helezonHiz = helezonHiz;
	}

	public float getEnjeksiyonHiz() {
		return enjeksiyonHiz;
	}

	public void setEnjeksiyonHiz(float enjeksiyonHiz) {
		this.enjeksiyonHiz = enjeksiyonHiz;
	}

	public float getDolumHiz() {
		return dolumHiz;
	}

	public void setDolumHiz(float dolumHiz) {
		this.dolumHiz = dolumHiz;
	}

	public float getKalipHiz() {
		return kalipHiz;
	}

	public void setKalipHiz(float kalipHiz) {
		this.kalipHiz = kalipHiz;
	}

	public float getAdetUcreti() {
		return adetUcreti;
	}

	public void setAdetUcreti(float adetUcreti) {
		this.adetUcreti = adetUcreti;
	}

	public float getKazanilanUcret() {
		return kazanilanUcret;
	}

	public void setKazanilanUcret(float kazanilanUcret) {
		this.kazanilanUcret = kazanilanUcret;
	}

	public float getEnjeksiyonHelezonFactor() {
		return enjeksiyonHelezonFactor;
	}

	public void setEnjeksiyonHelezonFactor(float enjeksiyonHelezonFactor) {
		this.enjeksiyonHelezonFactor = enjeksiyonHelezonFactor;
	}

	public float getParcaHacim() {
		return parcaHacim;
	}

	public void setParcaHacim(float parcaHacim) {
		this.parcaHacim = parcaHacim;
	}
	
	
	public Process() {
		
	}
	

}
