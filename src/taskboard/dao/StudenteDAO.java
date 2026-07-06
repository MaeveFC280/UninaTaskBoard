package taskboard.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import taskboard.boundary.DBConnection;

import java.sql.ResultSet;

public class StudenteDAO {

	public boolean autentica(String matricola, String password) throws SQLException{
		Connection con = DBConnection.getDBConnection().getConnection();
		String query = "SELECT 1 FROM Studente WHERE matricola = ?  AND password = ?"; //1 corrisponde a true da sql a java
		
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, matricola);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()){
				return rs.next();
			}
		}
	}
}
