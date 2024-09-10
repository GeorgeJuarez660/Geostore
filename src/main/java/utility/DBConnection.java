package src.main.java.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    //Directory del file db
    private static final String URL = "jdbc:sqlite:database/geostore.db";

    //Metodo per ottenere la connessione al db
    public static Connection sqlConnect(){
        Connection connection = null;

        try{
            Class.forName("org.sqlite.JDBC"); //driver sqlite
            connection = DriverManager.getConnection(URL); //creazione della connessione
            Utility.msgInf("GEOSTORE", "Connessione al db stabilita");
        }
        catch(SQLException e){ //errore connessione
            Utility.msgInf("GEOSTORE", "Errore alla connessione al db: " + e.getMessage());
        } catch (ClassNotFoundException e) { //errore driver
            Utility.msgInf("GEOSTORE", "Driver non caricato al db: " + e.getMessage());
        }

        return connection;
    }
}
