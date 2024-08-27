package src.main.java.model;

import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;
import src.main.java.utility.ordiniCRUD;

import java.sql.*;
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
            preparedStatement.setInt(2, o.getProdotto().getId());
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
        String sql = "SELECT o.id, c.nome AS nome_cliente, c.cognome AS cognome_cliente, og.nome AS nome_oggetto, o.data_ordine, o.quantita, o.prezzo_unitario, s.code AS stato FROM ordini o JOIN clienti c ON(o.cliente_id =c.id )\n" +
                "JOIN oggetti og ON(o.oggetto_id =og.id )\n" +
                "JOIN stato s ON(o.stato_id =s.id ) ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ordini = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Ordine ord;

            while(rs.next()){
                ord = new Ordine();
                ord.setId(rs.getInt("id"));
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCognome(rs.getString("cognome_cliente"));
                ord.setCliente(cliente);
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome_oggetto"));
                ord.setProdotto(prodotto);
                ord.setData_ordine(rs.getDate("data_ordine"));
                ord.setQuantita(rs.getInt("quantita"));
                ord.setPrezzo_unitario(rs.getBigDecimal("prezzo_unitario"));
                Stato stato = new Stato();
                stato.setCode(rs.getString("stato"));
                ord.setStato(stato);

                ordini.put(ord.getId(), ord);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOrdiniWithDB: " + e.getMessage());
        }

        return ordini;
    }

    public HashMap<Integer, Ordine> getOrdiniByUserWithDB(String nomeCliente) {
        String sql = "SELECT o.id, c.nome AS nome_cliente, c.cognome AS cognome_cliente, og.nome AS nome_oggetto, o.data_ordine, o.quantita, o.prezzo_unitario, s.code AS stato FROM ordini o JOIN clienti c ON(o.cliente_id =c.id )\n" +
                "JOIN oggetti og ON(o.oggetto_id =og.id )\n" +
                "JOIN stato s ON(o.stato_id =s.id )\n" +
                "WHERE nome_cliente = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ordini = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeCliente);
            rs = preparedStatement.executeQuery();
            Ordine ord;

            while(rs.next()){
                ord = new Ordine();
                ord.setId(rs.getInt("id"));
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCognome(rs.getString("cognome_cliente"));
                ord.setCliente(cliente);
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome_oggetto"));
                ord.setProdotto(prodotto);
                ord.setData_ordine(rs.getDate("data_ordine"));
                ord.setQuantita(rs.getInt("quantita"));
                ord.setPrezzo_unitario(rs.getBigDecimal("prezzo_unitario"));
                Stato stato = new Stato();
                stato.setCode(rs.getString("stato"));
                ord.setStato(stato);

                ordini.put(ord.getId(), ord);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOrdiniByUserWithDB: " + e.getMessage());
        }

        return ordini;
    }

    public Ordine getOrdineByUserAndObjNameWithDB(String nomeCliente, Integer idOrdine) {
        String sql = "SELECT o.id, c.nome AS nome_cliente, c.cognome AS cognome_cliente, og.nome AS nome_oggetto, o.data_ordine, o.quantita, o.prezzo_unitario, s.code AS stato FROM ordini o JOIN clienti c ON(o.cliente_id =c.id )\n" +
                "JOIN oggetti og ON(o.oggetto_id =og.id )\n" +
                "JOIN stato s ON(o.stato_id =s.id )\n" +
                "WHERE nome_cliente = ? AND o.id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Ordine ordine = new Ordine();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeCliente);
            preparedStatement.setInt(2, idOrdine);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                ordine.setId(rs.getInt("id"));
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCognome(rs.getString("cognome_cliente"));
                ordine.setCliente(cliente);
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome_oggetto"));
                ordine.setProdotto(prodotto);
                ordine.setData_ordine(rs.getDate("data_ordine"));
                ordine.setQuantita(rs.getInt("quantita"));
                ordine.setPrezzo_unitario(rs.getBigDecimal("prezzo_unitario"));
                Stato stato = new Stato();
                stato.setCode(rs.getString("stato"));
                ordine.setStato(stato);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOrdiniByUserWithDB: " + e.getMessage());
        }

        return ordine;
    }

    @Override
    public Ordine getOrdineWithDB(Integer id) {
        String sql = "SELECT o.id, c.nome AS nome_cliente, c.cognome AS cognome_cliente, og.nome AS nome_oggetto, o.data_ordine, o.quantita, o.prezzo_unitario, s.code AS stato FROM ordini o JOIN clienti c ON(o.cliente_id =c.id )\n" +
                "JOIN oggetti og ON(o.oggetto_id =og.id )\n" +
                "JOIN stato s ON(o.stato_id =s.id )\n" +
                "WHERE o.id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Ordine ordine = new Ordine();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                ordine.setId(rs.getInt("id"));
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCognome(rs.getString("cognome_cliente"));
                ordine.setCliente(cliente);
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome_oggetto"));
                ordine.setProdotto(prodotto);
                ordine.setData_ordine(rs.getDate("data_ordine"));
                ordine.setQuantita(rs.getInt("quantita"));
                ordine.setPrezzo_unitario(rs.getBigDecimal("prezzo_unitario"));
                Stato stato = new Stato();
                stato.setCode(rs.getString("stato"));
                ordine.setStato(stato);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOrdineWithDB: " + e.getMessage());
        }

        return ordine;
    }

    @Override
    public int updateOrdineWithDB(Integer id, Ordine newO) {
        String sql = "UPDATE `ordini` SET `stato_id` = ?, `quantita` = ?, `prezzo_unitario` = ? WHERE id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setInt(1, newO.getStato().getId());
            preparedStatement.setInt(2, newO.getQuantita());
            preparedStatement.setBigDecimal(3, newO.getPrezzo_unitario());
            preparedStatement.setInt(4, id);
            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel updateOrdineWithDB: " + e.getMessage());
        }

        return num;
    }

    @Override
    public void deleteOrdineWithDB(Integer id) {
        String sql = "DELETE FROM `ordini` WHERE id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel deleteOrdineWithDB: " + e.getMessage());
        }
    }

    //metodi override per operazioni CRUD




}
