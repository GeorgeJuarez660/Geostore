package src.main.java.model;

import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;
import src.main.java.utility.categorieCRUD;
import src.main.java.utility.materieCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MateriaRepository implements materieCRUD {

    private HashMap<Integer, Materia> materie = new HashMap<>();

    //metodi override per operazioni CRUD con database

    @Override
    public void insertMateriaWithDB(Integer id, Materia m) {
        String sql = "INSERT INTO `materie`(`nome`) VALUES (?) ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setString(1, m.getNome());
            preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel insertMateriaWithDB: " + e.getMessage());
        }
    }

    @Override
    public HashMap<Integer, Materia> getMaterieWithDB() {
        String sql = "SELECT * FROM Materie m";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        materie = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Materia mat;

            while(rs.next()){
                Utility.msgInf("GEOSTORE", "ID: " + rs.getInt("id") +
                        ", Nome: " + rs.getString("nome"));
                mat = new Materia();
                mat.setId(rs.getInt("id"));
                mat.setNome(rs.getString("nome"));

                materie.put(mat.getId(), mat);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getMaterieWithDB: " + e.getMessage());
        }

        return materie;
    }

    @Override
    public Materia getMateriaWithDB(String nome) {
        String sql = "SELECT * FROM Materie m WHERE m.NOME = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Materia mat = null;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                Utility.msgInf("GEOSTORE", "ID: " + rs.getInt("id") +
                        ", Nome: " + rs.getString("nome"));
                mat = new Materia();
                mat.setId(rs.getInt("id"));
                mat.setNome(rs.getString("nome"));
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getMateriaWithDB: " + e.getMessage());
        }
        return mat;
    }

    @Override
    public int updateMateriaWithDB(Integer id, Materia newM) {
        String sql = "UPDATE `materie` SET `nome` = ? WHERE id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setString(1, newM.getNome());
            preparedStatement.setInt(2, id);
            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel updateMateriaWithDB: " + e.getMessage());
        }

        return num;
    }

    @Override
    public void deleteMateriaWithDB(Integer id) {
        String sql = "DELETE FROM `materie` WHERE id = ? ";
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
            Utility.msgErr("GEOSTORE", "Errore nel deleteMateriaWithDB: " + e.getMessage());
        }
    }

}
