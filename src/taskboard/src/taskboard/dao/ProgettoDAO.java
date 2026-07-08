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
		String query = "SELECT nome, descrizione, pr.codiceInvito FROM Progetto pr JOIN Partecipazione p ON p.codiceInvito=pr.codiceInvito WHERE matricolaPartecipante = ?"; //1 corrisponde a true da sql a java
		
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
}
