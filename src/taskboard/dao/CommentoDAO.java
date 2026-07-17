package taskboard.dao;

import java.sql.*;
import java.util.*;

import taskboard.DBConnection;
import taskboard.entity.Commento;

public class CommentoDAO {

	public void creaComento(String testo, int idAttivita, String matricolaAutore) throws SQLException {
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "INSERT INTO Commento (testo, idAttivita, matricolastudente) VALUES (?,?,?)";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, testo);
			ps.setInt(2, idAttivita);
			ps.setString(3, matricolaAutore);
			ps.executeUpdate();
		}
	}
	
	public List<Commento> getCommenti (int idAttivita) throws SQLException{
		List<Commento> commenti = new ArrayList<>();
		
		Connection con =DBConnection.getDBConnection().getConnection();
		String query = "SELECT idCommento, testo, data, nome, cognome FROM Commento JOIN Studente ON matricolastudente = matricola WHERE idAttivita = ? ORDER BY idCommento";
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, idAttivita);
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					commenti.add(new Commento(rs.getInt("idCommento"), rs.getString("testo"),rs.getDate("data"),rs.getString("nome") + " " + rs.getString("cognome")));
				}
			}
		}
		return commenti;
	}

}
