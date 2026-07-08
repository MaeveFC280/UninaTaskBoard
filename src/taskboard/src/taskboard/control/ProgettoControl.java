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

}
