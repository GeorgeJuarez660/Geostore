package src.main.java.model;

import src.main.java.utility.CRUD;
import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Statement;

public class CategoriaRepository implements CRUD {

    private HashMap<Integer, Categoria> categorie = new HashMap<>();

    //metodi override per operazioni CRUD locali

    @Override
    public void insertCategoria(Integer id, Categoria c) {
        categorie.put(id, c);
        Utility.msgInf("GEOSTORE", "Categoria aggiunta");
    }

    @Override
    public HashMap<Integer, Categoria> getCategorie() {
        return categorie;
    }

    @Override
    public Categoria getCategoria(String nome) {
        Categoria foundCategoria = null;

        for(Categoria categoria : categorie.values()){
            if(categoria.getNome().equals(nome))
                foundCategoria = categoria;
        }
        return foundCategoria;
    }

    @Override
    public void updateCategoria(Integer id, Categoria newC) {

        if(categorie.containsKey(id)){
            categorie.put(id, newC);

            Utility.msgInf("GEOSTORE", "Categoria modificata");
        }
        else{
            Utility.msgErr("GEOSTORE", "Errore durante la modifica della categoria");
        }
    }

    @Override
    public boolean deleteCategoria(Integer id) {
        return categorie.remove(id) != null;
    }

    //metodi override per operazioni CRUD con database

    @Override
    public void insertCategoriaWithDB(Integer id, Categoria c) {

    }

    @Override
    public HashMap<Integer, Categoria> getCategorieWithDB() {
        String sql = "SELECT * FROM Categorie c";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while(rs.next()){
                Utility.msgInf("GEOSTORE", "ID: " + rs.getInt("id") +
                        ", Nome: " + rs.getString("nome"));
            }
            //chiudi la connessione
            rs.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getCategorieWithDB: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Categoria getCategoriaWithDB(String nome) {
        return null;
    }

    @Override
    public void updateCategoriaDB(Integer id, Categoria newC) {

    }

    @Override
    public boolean deleteCategoriaDB(Integer id) {
        return false;
    }


}
