package taskboard.entity;
import java.sql.*;

public abstract class Attivita {
	protected int id;
	protected String nome;
	protected String descrizione;
	protected Date dataCreazione;
	protected Date dataScadenza;
	protected String stato;
	protected String codiceProgetto;
	
	public Attivita(int id, String nome, String descrizione, Date dataCreazione, Date dataScadenza, String stato,
			String codiceProgetto) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataScadenza = dataScadenza;
		this.stato = stato;
		this.codiceProgetto = codiceProgetto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getCodiceProgetto() {
		return codiceProgetto;
	}

	public void setCodiceProgetto(String codiceProgetto) {
		this.codiceProgetto = codiceProgetto;
	}

	
	@Override
	public String toString() {
		return nome;
	}
	
}
