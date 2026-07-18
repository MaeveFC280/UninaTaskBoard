package taskboard.control;

import java.sql.SQLException;
import java.util.HashMap;

import taskboard.entity.Progetto;
import taskboard.dao.ReportDAO;

public class ReportControl {
	private ReportDAO dao = new ReportDAO();

	public int contaAttivita(Progetto p) throws SQLException{
		return dao.contaAttivita(p);
	}
	
	public int contaNONIniziati(Progetto p) throws SQLException{
		return dao.contaNONIniziati(p);
	}
	
	public int contaInCorso(Progetto p) throws SQLException{
		return dao.contaInCorso(p);
	}

	
	public int contaCompletati(Progetto p) throws SQLException{
		return dao.contaCompletati(p);
	}

	public int contaSviluppo(Progetto p) throws SQLException{
		return dao.contaSviluppo(p);
	}

	public HashMap<String, Integer> statisticheStudente(Progetto p) throws SQLException{
		return dao.statisticheStudente(p);
	}

}
