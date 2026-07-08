package taskboard.dao;

import java.sql.*;
import java.util.*;

import taskboard.DBConnection;
import taskboard.entity.Progetto;


public class ProgettoDAO {
	List<Progetto> progetti;

	public List<Progetto> getProgettiUtenti(String matricola) throws SQLException{
		progetti = new ArrayList<>();
		
		Connection con = DBConnection.getDBConnection().getConnection();
		String query = "SELECT nome, descrizione, codiceInvito FROM Progetto JOIN Partecipazione ON codiceInvito=codiceProgetto WHERE matricolaStudente = ?"; //1 corrisponde a true da sql a java
		
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, matricola);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Progetto p = new Progetto(
							rs.getString("codiceInvito"),
							rs.getString("nome"),
							rs.getString("descrizione"));
					progetti.add(p);
				}
			}
		}
		return progetti;
	}
	
	public boolean esisteProgetto(String codiceInvito) throws SQLException{
		Connection con = DBConnection.getDBConnection().getConnection();
		String query = "SELECT 1 FROM Progetto WHERE codiceInvito=?"; 
		
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, codiceInvito);
			try(ResultSet rs = ps.executeQuery()){
				return rs.next();
			}
		}
	}
	
	public void entraInProgetto(String matricola, String codiceInvito) throws SQLException{
		Connection con = DBConnection.getDBConnection().getConnection();
		String query = "INSERT INTO Partecipazione VALUES (?,?)"; 
		
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, matricola);
			ps.setString(2, codiceInvito);
			ps.executeUpdate();
		}
	}
	
	public void creaProgetto(String codiceInvito, String nome, String descrizione, String matricolaCreatore) throws SQLException{
		Connection con = DBConnection.getDBConnection().getConnection();
		String query1 = "INSERT INTO Progetto VALUES (?,?,?)"; 
		String query2 = "INSERT INTO Creazione VALUES (?,?)";
		String query3 = "INSERT INTO Partecipazione VALUES (?,?)"; //riscrivo per far funziona la transazione
		
		try {
				con.setAutoCommit(false); //transazione
		
			try (PreparedStatement ps = con.prepareStatement(query1)){
				ps.setString(1, codiceInvito);
				ps.setString(2, nome);
				ps.setString(3, descrizione);
				ps.executeUpdate();
			}
			
			try (PreparedStatement ps = con.prepareStatement(query2)){
				ps.setString(1, codiceInvito);
				ps.setString(2, matricolaCreatore);
				ps.executeUpdate();
			}
			
			try (PreparedStatement ps = con.prepareStatement(query3)){
				ps.setString(1, matricolaCreatore);
				ps.setString(2, codiceInvito);
				ps.executeUpdate();
			}
			con.commit();
		}catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			con.setAutoCommit(true);
		}
	}
}
