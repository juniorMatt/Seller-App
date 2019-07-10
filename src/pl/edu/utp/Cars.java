package pl.edu.utp;

import java.sql.Timestamp;

public class Cars {
	
	private String marka;
	private String model;
	private int cena;
	private String typNadwozia;
	private String kolor;
	private Timestamp czas;
	private int id;
	
	public Cars(int Id, String Marka, String Model, int Cena, String TypNadwozia, String Kolor,Timestamp Czas) {
		this.marka = Marka;
		this.model = Model;
		this.cena = Cena;
		this.typNadwozia = TypNadwozia;
		this.kolor = Kolor;
		this.czas = Czas;
		this.id = Id;
	}

	public Timestamp getCzas() {
		return czas;
	}

	public String getMarka() {
		return marka;
	}

	public String getModel() {
		return model;
	}

	public int getCena() {
		return cena;
	}

	public String getTypNadwozia() {
		return typNadwozia;
	}

	public String getKolor() {
		return kolor;
	}

	public int getId() {
		return id;
	}

	
	
}
