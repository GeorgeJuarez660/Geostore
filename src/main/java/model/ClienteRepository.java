package src.main.java.model;

import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;
import src.main.java.utility.clientiCRUD;
import src.main.java.utility.oggettiCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ClienteRepository implements clientiCRUD {

    private HashMap<Integer, Cliente> clienti = new HashMap<>();

    @Override
    public void insertClienteWithDB(Integer id, Cliente c) {

    }

    @Override
    public HashMap<Integer, Cliente> getClientiWithDB() {
        return null;
    }

    @Override
    public Cliente getClienteWithDB(String nome) {
        return null;
    }

    public Cliente checkUser(String email) {
        String sql = "select * from clienti c where c.email = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Cliente foundCliente = new Cliente();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                foundCliente.setId(rs.getInt("id"));
                foundCliente.setNome(rs.getString("nome"));
                foundCliente.setCognome(rs.getString("cognome"));
                foundCliente.setEmail(rs.getString("email"));
                foundCliente.setIndirizzo(rs.getString("indirizzo"));
                foundCliente.setTelefono(rs.getString("telefono"));

            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel checkUser: " + e.getMessage());
        }

        return foundCliente;
    }

    @Override
    public void updateClienteWithDB(Integer id, Cliente newC) {

    }

    @Override
    public void deleteClienteWithDB(Integer id) {

    }

    //metodi override per operazioni CRUD




}
