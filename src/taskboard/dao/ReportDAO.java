package taskboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import taskboard.DBConnection;
import taskboard.entity.Progetto;

public class ReportDAO {

	public int contaAttivita(Progetto p) throws SQLException{
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT COUNT(*) FROM Attivita WHERE codiceprogetto = ?";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, p.getCodiceInvito());
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	
	public int contaNONIniziati(Progetto p) throws SQLException{
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT COUNT(*) FROM Attivita WHERE codiceprogetto = ? AND stato = 'NON INIZIATO'";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, p.getCodiceInvito());
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				return rs.getInt(1);
			}
		}
	}
	
	public int contaInCorso(Progetto p) throws SQLException{
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT COUNT(*) FROM Attivita WHERE codiceprogetto = ? AND stato = 'IN CORSO'";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, p.getCodiceInvito());
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	
	public int contaCompletati(Progetto p) throws SQLException{
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT COUNT(*) FROM Attivita WHERE codiceprogetto = ? AND stato = 'COMPLETATO'";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, p.getCodiceInvito());
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	public int contaSviluppo(Progetto p) throws SQLException{
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT COUNT(*) FROM Attivita WHERE codiceprogetto = ? AND tipo = 'SVILUPPO'";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, p.getCodiceInvito());
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	public HashMap<String, Integer>  statisticheStudente(Progetto p) throws SQLException{ 
		HashMap<String, Integer> completamentiStudente = new HashMap<>();
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT COUNT(*) as completamenti, Studente.nome, Studente.cognome FROM Assegnazione JOIN Attivita ON idattivita=id JOIN Studente "
				+ "on matricolastudente=matricola WHERE codiceprogetto = ? and stato='COMPLETATO' GROUP BY matricolastudente, Studente.nome, cognome";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, p.getCodiceInvito());
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					completamentiStudente.put(rs.getString("nome") + " " + rs.getString("cognome"),rs.getInt("completamenti"));
				}
			}
		}
		return completamentiStudente;
	}


}
