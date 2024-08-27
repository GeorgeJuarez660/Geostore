package src.main.java.model;

import src.main.java.utility.DBConnection;
import src.main.java.utility.Utility;
import src.main.java.utility.prodottiCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProdottoRepository implements prodottiCRUD {

    private HashMap<Integer, Prodotto> prodotti = new HashMap<>();

    @Override
    public void insertProdottoWithDB(Integer id, Prodotto p) {

    }

    @Override
    public HashMap<Integer, Prodotto> getProdottiWithDB() {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id) ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        prodotti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Prodotto ogg;

            while(rs.next()){
                ogg = new Prodotto();
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

                prodotti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getProdottiWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    public HashMap<Integer, Prodotto> getProdottiDispWithDB() {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where disponibilita_code = 'DISPONIBILE' OR disponibilita_code = 'ESAURIMENTO'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        prodotti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            Prodotto p;

            while(rs.next()){
                p = new Prodotto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                p.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                p.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                p.setMateria(materia);
                p.setQuantita_disp(rs.getInt("quantita_disp"));

                prodotti.put(p.getId(), p);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getProdottiDispWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    public HashMap<Integer, Prodotto> getProdottiViaCategoriaWithDB(String nomeCat) {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where categoria_code = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        prodotti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeCat);
            rs = preparedStatement.executeQuery();
            Prodotto ogg;

            while(rs.next()){
                ogg = new Prodotto();
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

                prodotti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getProdottiViaCategoriaWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    public HashMap<Integer, Prodotto> getProdottiViaMateriaWithDB(String nomeMat) {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where materia_code = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        prodotti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeMat);
            rs = preparedStatement.executeQuery();
            Prodotto ogg;

            while(rs.next()){
                ogg = new Prodotto();
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

                prodotti.put(ogg.getId(), ogg);
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getProdottiViaMateriaWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    @Override
    public Prodotto getProdottoWithDB(String nome) {
        String sql = "select * from prodotti o ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Prodotto prodotto = new Prodotto();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                prodotto.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                prodotto.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                prodotto.setMateria(materia);
                prodotto.setQuantita_disp(rs.getInt("quantita_disp"));
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getProdottoWithDB: " + e.getMessage());
        }

        return prodotto;
    }

    public Prodotto getProdottoDispWithDB(String nome) {
        String sql = "select o.id, o.nome, o.prezzo, d.code as disponibilita_code, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o join Disponibilita d on (o.disponibilita = d.id)\n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.nome = ? AND (disponibilita_code = 'DISPONIBILE' OR disponibilita_code = 'ESAURIMENTO')";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Prodotto prodotto = new Prodotto();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setCode(rs.getString("disponibilita_code"));
                prodotto.setDisponibilita(disponibilita);
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("categoria_code"));
                prodotto.setCategoria(categoria);
                Materia materia = new Materia();
                materia.setNome(rs.getString("materia_code"));
                prodotto.setMateria(materia);
                prodotto.setQuantita_disp(rs.getInt("quantita_disp"));
            }
            //chiudi la connessione
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgErr("GEOSTORE", "Errore nel getProdottoDispWithDB: " + e.getMessage());
        }

        return prodotto;
    }

    @Override
    public void updateProdottoWithDB(Integer id, Prodotto newP) {

    }

    @Override
    public void deleteProdottoWithDB(Integer id) {

    }

    //metodi override per operazioni CRUD




}
