package src.main.java.model;

import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;
import src.main.java.utility.oggettiCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OggettoRepository implements oggettiCRUD {

    private HashMap<Integer, Oggetto> oggetti = new HashMap<>();

    @Override
    public void insertOggettoWithDB(Integer id, Oggetto o) {

    }

    @Override
    public HashMap<Integer, Oggetto> getOggettiWithDB() {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from oggetti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id) ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        oggetti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Oggetto ogg;

            while(rs.next()){
                ogg = new Oggetto();
                ogg.setId(rs.getInt("id"));
                ogg.setNome(rs.getString("nome"));
                ogg.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                ogg.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                ogg.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                ogg.setMateria(materia);
                ogg.setQuantita_disp(rs.getInt("quantita_disp"));

                oggetti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOggettiWithDB: " + e.getMessage());
        }

        return oggetti;
    }

    public HashMap<Integer, Oggetto> getOggettiDispWithDB() {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from oggetti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where disponibilita_code = 'DISPONIBILE' OR disponibilita_code = 'ESAURIMENTO'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        oggetti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Oggetto ogg;

            while(rs.next()){
                ogg = new Oggetto();
                ogg.setId(rs.getInt("id"));
                ogg.setNome(rs.getString("nome"));
                ogg.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                ogg.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                ogg.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                ogg.setMateria(materia);
                ogg.setQuantita_disp(rs.getInt("quantita_disp"));

                oggetti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOggettiDispWithDB: " + e.getMessage());
        }

        return oggetti;
    }

    public HashMap<Integer, Oggetto> getOggettiViaCategoriaWithDB(String nomeCat) {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from oggetti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where categoria_code = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        oggetti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeCat);
            rs = preparedStatement.executeQuery();
            Oggetto ogg;

            while(rs.next()){
                ogg = new Oggetto();
                ogg.setId(rs.getInt("id"));
                ogg.setNome(rs.getString("nome"));
                ogg.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                ogg.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                ogg.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                ogg.setMateria(materia);
                ogg.setQuantita_disp(rs.getInt("quantita_disp"));

                oggetti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOggettiViaCategoriaWithDB: " + e.getMessage());
        }

        return oggetti;
    }

    public HashMap<Integer, Oggetto> getOggettiViaMateriaWithDB(String nomeMat) {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from oggetti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where materia_code = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        oggetti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeMat);
            rs = preparedStatement.executeQuery();
            Oggetto ogg;

            while(rs.next()){
                ogg = new Oggetto();
                ogg.setId(rs.getInt("id"));
                ogg.setNome(rs.getString("nome"));
                ogg.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                ogg.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                ogg.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                ogg.setMateria(materia);
                ogg.setQuantita_disp(rs.getInt("quantita_disp"));

                oggetti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getOggettiViaMateriaWithDB: " + e.getMessage());
        }

        return oggetti;
    }

    @Override
    public Oggetto getOggettoWithDB(String nome) {
        String sql = "select * from oggetti o ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Oggetto oggetto = new Oggetto();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                oggetto.setId(rs.getInt("id"));
                oggetto.setNome(rs.getString("nome"));
                oggetto.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                oggetto.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                oggetto.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                oggetto.setMateria(materia);
                oggetto.setQuantita_disp(rs.getInt("quantita_disp"));
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel checkUser: " + e.getMessage());
        }

        return oggetto;
    }

    public Oggetto getOggettoDispWithDB(String nome) {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from oggetti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.nome = ? AND (disponibilita_code = 'DISPONIBILE' OR disponibilita_code = 'ESAURIMENTO')";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Oggetto oggetto = new Oggetto();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                oggetto.setId(rs.getInt("id"));
                oggetto.setNome(rs.getString("nome"));
                oggetto.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                oggetto.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                oggetto.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                oggetto.setMateria(materia);
                oggetto.setQuantita_disp(rs.getInt("quantita_disp"));
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel checkUser: " + e.getMessage());
        }

        return oggetto;
    }

    @Override
    public void updateOggettoWithDB(Integer id, Oggetto newO) {

    }

    @Override
    public void deleteOggettoWithDB(Integer id) {

    }

    //metodi override per operazioni CRUD




}
