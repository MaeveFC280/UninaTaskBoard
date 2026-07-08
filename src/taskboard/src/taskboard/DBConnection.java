package taskboard;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnection
{
    // istanza statica e privata di questa classe
    private static DBConnection dbcon = null;
    // istanza privata della connessione ad SQL
    private Connection conn = null;

    // costruttore private
    private DBConnection(){}

    // metodo statico pubblico per ottenere una istanza della classe privata
    public static DBConnection getDBConnection()
    {   // se la classe connessione è nulla, la crea
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        System.out.println("Creata classe DBConnection");
        // e la restituisce
        return dbcon;
    }

    // metodo pubblico per ottenere la connessione
    public Connection getConnection()
    {
        String pwd = null;
        BufferedReader b = null;
        try
        {   // se la connessione non esiste oppure è stata erroneamente chiusa
            if(conn==null || conn.isClosed())
            {   //legge la pwd di connessione al DB dal file
                b = new BufferedReader(new FileReader(new File("src/pwdfile")));
                pwd = b.readLine();
                
                // registra il driver, in questo caso quello di PostgresSQL.
                // NOTA: va fatto SOLO PER JDBC < 4.0 !!
                // il JAR contenente il driver è nelle librerie (esterne) di build del progetto
                //Class.forName("org.postgresql.Driver");
                
                // chiama il DriverManager e chiedi la connessione al DBMS
                // L'URL specifica il protocollo, l'indirizzo e la porta. Inoltre 
                // viene specificato anche il DATABASE (Esercitazioni_corso_BDD, l'utente (postgres) e la password)
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaTaskBoard", "postgres", pwd);
            }
        //} catch (SQLException | IOException | ClassNotFoundException throwables) {
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }


    // metodo pubblico per ottenere la connessione, in questo caso passo anche il nome dello schema a cui connettermi
    public Connection getConnection(String schema_name) 
    {
    	
    	System.out.println("Richiesta connessione...");
        String pwd = null;
        BufferedReader b = null;
        if (Objects.equals(schema_name, ""))
            throw new RuntimeException("Schema name is empty");
        try
        {   // se la connessione non esiste oppure è stata chiusa
            if(conn==null || conn.isClosed())
            {   //legge la pwd dal file
                b = new BufferedReader(new FileReader(new File("src/pwdfile")));
                pwd = b.readLine();
                
                // registra il driver, in questo caso quello di PostgresSQL.
                // NOTA: va fatto SOLO PER JDBC < 4.0 !!
                // il JAR contenente il driver è nelle librerie (esterne) di build del progetto
                //Class.forName("org.postgresql.Driver");
                
             // chiama il DriverManager e chiedi la connessione al DBMS
                // L'URL specifica il protocollo, l'indirizzo e la porta. Inoltre 
                // viene specificato anche il DATABASE (Esercitazioni_corso_BDD, l'utente (postgres) e la password)
                // In questo caso inoltre si passa dall'esterno il nome dello SCHEMA a cui connettersi
                String s_url="jdbc:postgresql://localhost:5432/UninaTaskBoard?currentSchema="+schema_name;
                //System.out.println("surl" + s_url);
                conn = DriverManager.getConnection(s_url, "postgres", pwd);
                System.out.println("...connessione ottenuta!");
            }
        //} catch (SQLException | IOException | ClassNotFoundException throwables) { // 
        } catch (SQLException | IOException throwables) {
        	System.err.println("Errore di connessione !");
            throwables.printStackTrace();
        }

        return conn;
    }

}
