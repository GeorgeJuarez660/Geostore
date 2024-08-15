package src.main.java.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.JDBC;


public class DBConnection {

    private static final String URL = "jdbc:sqlite:C:/Users/giorg/OneDrive/Desktop/App/G&P/DB/geostore.db";

    //Metodo per ottenere la connessione al db
    public static Connection sqlConnect(){
        Connection connection = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            Utility.msgInf("GEOSTORE", "Connessione al db stabilita");
        }
        catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore alla connessione al db: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Utility.msgErr("GEOSTORE", "Driver non caricato al db: " + e.getMessage());
        }

        return connection;
    }
}
