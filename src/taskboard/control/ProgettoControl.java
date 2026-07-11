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
	
	public void creaProgetto(String nome, String descrizione, String matricolaCreatore) throws SQLException{
		String codiceInvito = UUID.randomUUID().toString().substring(0,8);
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
