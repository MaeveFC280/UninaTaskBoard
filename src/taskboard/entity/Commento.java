package taskboard.entity;

import java.sql.Date;

public class Commento {
	
	private int id;
	private String testo;
	private Date data;
	private String autore;
	
	public Commento(int id, String testo, Date data, String autore) {
		super();
		this.id = id;
		this.testo = testo;
		this.data = data;
		this.autore = autore;
	}
	@Override
	public String toString() {
		return autore + " (" + data + "):" + testo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}

	

}
