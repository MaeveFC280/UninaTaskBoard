package taskboard.control;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import taskboard.dao.AttivitaDAO;
import taskboard.entity.Attivita;
import taskboard.entity.Studente;

public class AttivitaControl {
	private AttivitaDAO DAO = new AttivitaDAO();

	public void creaAttivita(String nome, String descrizione, Date dataScadenza, String tipo, String codiceProgetto, List<Studente> responsabili) throws SQLException{
		
		if (dataScadenza != null) {
		    java.sql.Date oggi =
		            java.sql.Date.valueOf(LocalDate.now());

		    if (dataScadenza.before(oggi)) {
		        throw new IllegalArgumentException(
		            "La data di scadenza non può essere precedente alla data odierna."
		        );
		    }
		}
		
		if(responsabili.isEmpty()) {
			throw new IllegalArgumentException("Seleziona almeno uno studente responsabile.");
		}
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Inserisci un nome per l'attività");
		}
		if(nome.length()>100) {
			throw new IllegalArgumentException("Il nome dell'attività non può essere più lungo di 100 caratteri");
		}
		if(descrizione!= null && descrizione.length()>200) {
			throw new IllegalArgumentException("La descrizione dell'attività non può essere più lunga di 200 caratteri");
		}
		if("SVILUPPO".equalsIgnoreCase(tipo) || "DOCUMENTAZIONE".equalsIgnoreCase(tipo)) {
			DAO.creaAttivita(nome, descrizione, dataScadenza, "NON INIZIATO", tipo, codiceProgetto, responsabili);
		}else {
			throw new IllegalArgumentException("Tipo di attività non valido: " + tipo);
		}
	}
	
	public List<Attivita> getAttivita(String codiceProgetto) throws SQLException{
		List<Attivita> lista = DAO.getAttivita(codiceProgetto);
		java.util.Date oggi = java.sql.Date.valueOf(LocalDate.now());
		for(Attivita a :lista) {
			if(a.getDataScadenza() != null && a.getDataScadenza().before(oggi) && !"COMPLETATO".equals(a.getStato())) {
				scadenzaAttivita(a);
			}
		}
		return lista;
	}
	
	public List<Studente> getResponsabili(int id) throws SQLException{
		return DAO.getResponsabili(id);	
	}
	
	public List<Studente> getPartecipanti (String id) throws SQLException{
		return DAO.getPartecipanti(id);
	}
	
	public void completaAttivita (Attivita a) throws SQLException{
		DAO.aggiornaStato(a.getId(), "COMPLETATO");
		a.setStato("COMPLETATO");
	}
	
	
	public void svolgimentoAttivita (Attivita a) throws SQLException{
		DAO.aggiornaStato(a.getId(), "IN CORSO");
		a.setStato("IN CORSO");
	}
	
	public void scadenzaAttivita (Attivita a) throws SQLException{
		DAO.aggiornaStato(a.getId(), "SCADUTO");
		a.setStato("SCADUTO");
	}
	
	public List<Attivita> filtraAttivita(String codiceProgetto, String stato, String tipo, String matricolaMembro, Date scadenzaEntro) throws SQLException{
		return DAO.filtraAttivita(codiceProgetto, stato, tipo, matricolaMembro, scadenzaEntro);
	}
}

