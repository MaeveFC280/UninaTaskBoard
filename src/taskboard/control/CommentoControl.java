package taskboard.control;

import java.sql.SQLException;
import java.util.List;

import taskboard.dao.CommentoDAO;
import taskboard.entity.Commento;

public class CommentoControl {

	private CommentoDAO DAO = new CommentoDAO();
	
	public void creaCommento (String testo, int idAttivita, String matricolaAutore) throws SQLException{
		if (testo == null || testo.trim().isEmpty()) {
			throw new IllegalArgumentException("Il commento non può essere vuoto");
		}
		DAO.creaComento(testo.trim(), idAttivita, matricolaAutore);
	}
	
	public List<Commento> getCommenti (int idAttivita) throws SQLException{
		return DAO.getCommenti(idAttivita);
	}

}
