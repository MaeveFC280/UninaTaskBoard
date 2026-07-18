package taskboard.entity;

import java.sql.Date;

public class AttivitaSviluppo extends Attivita {

	public AttivitaSviluppo(int id, String nome, String descrizione, Date dataCreazione, Date dataScadenza,
			String stato, String codiceProgetto) {
		super(id, nome, descrizione, dataCreazione, dataScadenza, stato, codiceProgetto);
		// TODO Auto-generated constructor stub
	}

}
