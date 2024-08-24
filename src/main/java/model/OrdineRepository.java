package src.main.java.model;

import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;
import src.main.java.utility.oggettiCRUD;
import src.main.java.utility.ordiniCRUD;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class OrdineRepository implements ordiniCRUD {

    private HashMap<Integer, Ordine> ordini = new HashMap<>();

    @Override
    public int insertOrdineWithDB(Integer id, Ordine o) {
        String sql = "INSERT INTO `ordini`(`cliente_id`, `oggetto_id`,`data_ordine`,`quantita`,`prezzo_unitario`,`stato_id`) VALUES (?, ?, ?, ?, ?, ?) ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setInt(1, o.getCliente().getId());
            preparedStatement.setInt(2, o.getOggetto().getId());
            preparedStatement.setDate(3, o.getData_ordine());
            preparedStatement.setInt(4, o.getQuantita());
            preparedStatement.setBigDecimal(5, o.getPrezzo_unitario());
            preparedStatement.setInt(6, o.getStato().getId());
            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel insertOrdineWithDB: " + e.getMessage());
        }

        return num;
    }

    @Override
    public HashMap<Integer, Ordine> getOrdiniWithDB() {
        return null;
    }

    @Override
    public Ordine getOrdineWithDB(String nome) {
        return null;
    }

    @Override
    public void updateOrdineWithDB(Integer id, Ordine newO) {

    }

    @Override
    public void deleteOrdineWithDB(Integer id) {

    }

    //metodi override per operazioni CRUD




}
