package src.main.java.model;

import src.main.java.utility.CRUD;
import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;

import java.sql.*;
import java.util.HashMap;

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
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Categoria cat;

            while(rs.next()){
                Utility.msgInf("GEOSTORE", "ID: " + rs.getInt("id") +
                        ", Nome: " + rs.getString("nome"));
                cat = new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setNome(rs.getString("nome"));

                categorie.put(cat.getId(), cat);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getCategorieWithDB: " + e.getMessage());
        }

        return categorie;
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
