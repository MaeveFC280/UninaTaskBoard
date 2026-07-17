package taskboard.control;

import java.sql.*;
import java.util.*;
import taskboard.dao.ProgettoDAO;
import taskboard.entity.Progetto;

public class ProgettoControl {
	
	private ProgettoDAO progettoDAO = new ProgettoDAO();
	
	public List<Progetto> getProgettiUtente(String matricola) throws SQLException{
		return progettoDAO.getProgettiUtenti(matricola);
	}
	
	public void creaProgetto(String matricolaCreatore, String nome, String descrizione) throws SQLException{
		String codiceInvito = UUID.randomUUID().toString().substring(0,8);
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Inserisci un nome per l'attività");
		}
		if(nome.length()>50) {
			throw new IllegalArgumentException("Il nome del progetto non può essere più lungo di 50 caratteri");
		}
		if(descrizione!= null && descrizione.length()>200) {
			throw new IllegalArgumentException("La descrizione del progetto non può essere più lunga di 200 caratteri");
		}
		progettoDAO.creaProgetto(codiceInvito, nome, descrizione, matricolaCreatore);
	}
	
	public boolean entraInProgetto(String matricola, String codiceInvito) throws SQLException{
		if (!progettoDAO.esisteProgetto(codiceInvito)) {
			return false;
		}
		progettoDAO.entraInProgetto(matricola, codiceInvito);
		return true;
	}

}
