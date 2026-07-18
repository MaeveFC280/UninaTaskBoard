package taskboard.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taskboard.DBConnection;
import taskboard.entity.Attivita;
import taskboard.entity.AttivitaDocumentazione;
import taskboard.entity.AttivitaSviluppo;
import taskboard.entity.Studente;

public class AttivitaDAO {
		
		public void creaAttivita(String nome, String descrizione, Date dataScadenza,
				String stato, String tipo, String codiceProgetto, List<Studente> responsabili) throws SQLException{
			Connection con = DBConnection.getDBConnection().getConnection();
			String query1 = "INSERT INTO Attivita (nome, descrizione, dataScadenza, dataCreazione, stato,tipo, codiceprogetto) "
					+ "VALUES (?,?,?, CURRENT_DATE,?,?,?)";
			String query2 = "INSERT INTO Assegnazione (idattivita, matricolaStudente) VALUES (?,?)";
			
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
						if ("SVILUPPO".equalsIgnoreCase(rs.getString("tipo"))) {
							a = new AttivitaSviluppo(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"), rs.getDate("dataCreazione"),
									rs.getDate("dataScadenza"), rs.getString("stato"), codiceProgetto);
						}else if("DOCUMENTAZIONE".equalsIgnoreCase(rs.getString("tipo"))) {
							a = new AttivitaDocumentazione(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"), rs.getDate("dataCreazione"),
									rs.getDate("dataScadenza"), rs.getString("stato"), codiceProgetto);
						}else {
							throw new IllegalArgumentException("Tipo attività non valido: "+ rs.getString("tipo"));
						}
						attivita.add(a);
					}
				}
			}
			return attivita;
			
		}
		
		public List<Studente> getResponsabili(int id) throws SQLException{
			Connection con = DBConnection.getDBConnection().getConnection();
			List<Studente> responsabili = new ArrayList<>();
			String query = "SELECT matricola, nome, cognome FROM assegnazione join studente on matricola=matricolaStudente where idattivita = ?";
			
			try(PreparedStatement ps = con.prepareStatement(query)){
				ps.setInt(1, id);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						responsabili.add(new Studente(rs.getString("matricola"), rs.getString("nome"), rs.getString("cognome")));
					}
				}
				return responsabili;
			}
		}
		
		public List<Studente> getPartecipanti (String id) throws SQLException{
			Connection con = DBConnection.getDBConnection().getConnection();
			List<Studente> partecipanti = new ArrayList<>();
			String query = "SELECT matricola, nome, cognome FROM partecipazione JOIN studente on matricola=matricolastudente WHERE codiceprogetto = ?";
			
			try(PreparedStatement ps = con.prepareStatement(query)){
				ps.setString(1, id);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						partecipanti.add(new Studente(rs.getString("matricola"), rs.getString("nome"), rs.getString("cognome")));
					}
				}
				return partecipanti;
			}
		}
		
		public void aggiornaStato ( int id, String stato) throws SQLException{
			Connection con = DBConnection.getDBConnection().getConnection();
			String q ="UPDATE Attivita SET stato = ? WHERE id = ?";
			
			try(PreparedStatement ps = con.prepareStatement(q)){
				ps.setInt(2, id);
				ps.setString(1, stato);
				ps.executeUpdate();
			}
		}
		
		public List<Attivita> filtraAttivita(String codiceProgetto, String stato, String tipo,
				String matricolaResponsabile, Date scadenza) throws SQLException{
			List<Attivita> risultato = new ArrayList();
			Connection con = DBConnection.getDBConnection().getConnection();
			
			//SQL Dinamico per mettere insieme criteri
			StringBuilder query = new StringBuilder("SELECT id,nome,descrizione,datacreazione,datascadenza,stato,tipo FROM Attivita WHERE codiceProgetto=? ");
			List<Object> parametri = new ArrayList<>();
			parametri.add(codiceProgetto);
			
			//possibile parametri
			if (stato != null) {
				query.append("AND stato =? ");
				parametri.add(stato);
			}
			if (tipo != null) {
				query.append("AND tipo =? ");
				parametri.add(tipo);
			}
			if (matricolaResponsabile != null) {
				query.append("AND id IN (SELECT idattivita FROM Assegnazione WHERE matricolaStudente = ? )");
				parametri.add(matricolaResponsabile);
			}
			if (scadenza != null) {
				query.append("AND dataScadenza <=? ");
				parametri.add(scadenza);
			}
			
			try(PreparedStatement ps = con.prepareStatement(query.toString())){
				for (int i = 0; i<parametri.size(); i++) {
					ps.setObject(i+1, parametri.get(i));
				}
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						Attivita a;
						if ("SVILUPPO".equalsIgnoreCase(rs.getString("tipo"))) {
							a = new AttivitaSviluppo(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"), rs.getDate("dataCreazione"),
									rs.getDate("dataScadenza"), rs.getString("stato"), codiceProgetto);
						}else if("DOCUMENTAZIONE".equalsIgnoreCase(rs.getString("tipo"))) {
							a = new AttivitaDocumentazione(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"), rs.getDate("dataCreazione"),
									rs.getDate("dataScadenza"), rs.getString("stato"), codiceProgetto);
						}else {
							throw new IllegalArgumentException("Tipo attività non valido: "+ rs.getString("tipo"));
						}
						risultato.add(a);
					}
				}
			}
			return risultato;
		}
}
