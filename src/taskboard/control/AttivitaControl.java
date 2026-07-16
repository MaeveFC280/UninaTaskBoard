package taskboard.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taskboard.DBConnection;
import taskboard.dao.AttivitaDAO;
import taskboard.entity.Attivita;
import taskboard.entity.AttivitaDocumentazione;
import taskboard.entity.AttivitaSviluppo;
import taskboard.entity.Studente;

public class AttivitaControl {
	private AttivitaDAO DAO = new AttivitaDAO();

	public void creaAttivita(String nome, String descrizione, Date dataScadenza, String tipo, String codiceProgetto, List<Studente> responsabili) throws SQLException{
		if("SVILUPPO".equalsIgnoreCase(tipo) || "DOCUMENTAZIONE".equalsIgnoreCase(tipo)) {
			DAO.creaAttivita(nome, descrizione, dataScadenza, "NON INIZIATA", tipo, codiceProgetto, responsabili);
		}else {
			throw new IllegalArgumentException("Tipo di attività non valido: " + tipo);
		}
	}
	
	public List<Attivita> getAttivita(String codiceProgetto) throws SQLException{
		return DAO.getAttivita(codiceProgetto);
	}
	
	public List<Studente> getResponsabili(int id) throws SQLException{
		return DAO.getResponsabili(id);	
	}
	
	public List<Studente> getPartecipanti (String id) throws SQLException{
		return DAO.getPartecipanti(id);
	}
	
	public void completaAttivita ( int id) throws SQLException{
		DAO.aggiornaStato(id, "COMPLETATA");
	}
	
	public void scadenzaAttivita ( int id) throws SQLException{
		DAO.aggiornaStato(id, "SCADUTO");
	}
	
	public void svolgimentoAttivita ( int id) throws SQLException{
		DAO.aggiornaStato(id, "IN CORSO");
	}
}

