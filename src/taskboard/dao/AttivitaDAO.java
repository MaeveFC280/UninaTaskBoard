package taskboard.dao;

import java.util.*;

import taskboard.DBConnection;
import taskboard.entity.Attivita;
import taskboard.entity.AttivitaDocumentazione;
import taskboard.entity.AttivitaSviluppo;
import taskboard.entity.Studente;

import java.sql.*;
import java.sql.Date;

public class AttivitaDAO {

	public AttivitaDAO() {
		
		public void creaAttivita(String nome, String descrizione, Date dataScadenza,
				String stato, String tipo, String codiceProgetto, List<Studente> responsabili) throws SQLException{
			Connection con = DBConnection.getDBConnection().getConnection();
			String query1 = "INSERT INTO Attivita (nome, descrizione, dataScadenza, dataCreazione, stato,tipo, codiceprogetto) VALUES (?,?,?, CURRENT_DATE,?,?,?)";
			String query2 = "INSERT INTO Assegnazione (idattivita, matrcolaStudente) VALUES (?,?)";
			
			try {
				con.setAutoCommit(false);
				int idAttivita;
				
				try (PreparedStatement ps = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
					ps.setString(1, nome);
					ps.setString(2, descrizione);
					ps.setDate(3, dataScadenza);
					ps.setString(4, stato);
					ps.setString(5, tipo);
					ps.setString(6, codiceProgetto);
					ps.executeUpdate();
					
					try(ResultSet rs = ps.getGeneratedKeys()){
						rs.next();
						idAttivita=rs.getInt("id");
					}
				}
				
				try (PreparedStatement ps = con.prepareStatement(query2)){
					for (Studente studente : responsabili) {
						ps.setInt(1, idAttivita);
						ps.setString(2, studente.getMatricola());
						ps.executeUpdate();
					}
				}
				
				con.commit();
			}catch(SQLException e) {
				con.rollback();
				throw e;
			}finally {
				con.setAutoCommit(true);
			}

		}
		
		public List<Attivita> getAttivita(String codiceProgetto) throws SQLException{
			List<Attivita> attivita = new ArrayList<>();
			Connection con = DBConnection.getDBConnection().getConnection();
			
			String query = "SELECT id, nome, descrizione, datacreazione, datascadenza, stato, tipo FROM Attivita WHERE codiceProgetto = ?";
			try(PreparedStatement ps = con.prepareStatement(query)){
				ps.setString(1, codiceProgetto);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						Attivita a;
						if ("SVILUPPO".equalsIgnoreCase(rs.getNString("tipo"))) {
							a = new AttivitaSviluppo(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"), rs.getDate("dataCreazione"),
									rs.getDate("dataScadenza"), rs.getString("stato"), codiceProgetto);
						}else if("DOCUMENTAZIONE".equalsIgnoreCase(rs.getNString("tipo"))) {
							a = new AttivitaDocumentazione(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"), rs.getDate("dataCreazione"),
									rs.getDate("dataScadenza"), rs.getString("stato"), codiceProgetto);
						}
						attivita.add(a);
					}
				}
			}
			return attivita;
			
		}
}
