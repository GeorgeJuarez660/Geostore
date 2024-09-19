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
    public int insertProdottoWithDB(Integer id, Prodotto p) {
        String sql = "INSERT INTO `prodotti`(`nome`,`prezzo`,`disponibilita`,`categoria`,`materia`,`quantita_disp`) VALUES (?, ?, ?, ?, ?, ?) ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setString(1, p.getNome());
            preparedStatement.setBigDecimal(2, p.getPrezzo());
            preparedStatement.setInt(3, p.getDisponibilita().fromStringToInt(p.getDisponibilita().getCode()));
            preparedStatement.setInt(4, p.getCategoria().getId());
            preparedStatement.setInt(5, p.getMateria().getId());
            preparedStatement.setInt(6, p.getQuantita_disp());

            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgInf("GEOSTORE", "Errore nel insertProdottoWithDB: " + e.getMessage());
        }

        return num;
    }

    @Override
    public HashMap<Integer, Prodotto> getProdottiWithDB() {
        String sql = "select o.id, o.nome, o.prezzo, o.disponibilita, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o \n" +
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
                disponibilita.setId(rs.getInt("disponibilita"));
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
            Utility.msgInf("GEOSTORE", "Errore nel getProdottiWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    public HashMap<Integer, Prodotto> getProdottiDispWithDB() {
        String sql = "select o.id, o.nome, o.prezzo, o.disponibilita, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o \n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.disponibilita = 1 OR o.disponibilita = 3";
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
                disponibilita.setId(rs.getInt("disponibilita"));
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
            Utility.msgInf("GEOSTORE", "Errore nel getProdottiDispWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    public HashMap<Integer, Prodotto> getProdottiViaCategoriaWithDB(Integer idCat) {
        String sql = "select o.id, o.nome, o.prezzo, o.disponibilita, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o \n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.categoria = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        prodotti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCat);
            rs = preparedStatement.executeQuery();
            Prodotto ogg;

            while(rs.next()){
                ogg = new Prodotto();
                ogg.setId(rs.getInt("id"));
                ogg.setNome(rs.getString("nome"));
                ogg.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setId(rs.getInt("disponibilita"));
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
            Utility.msgInf("GEOSTORE", "Errore nel getProdottiViaCategoriaWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    public HashMap<Integer, Prodotto> getProdottiViaMateriaWithDB(Integer idMat) {
        String sql = "select o.id, o.nome, o.prezzo, o.disponibilita, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o \n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.materia = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        prodotti = new HashMap<>();

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idMat);
            rs = preparedStatement.executeQuery();
            Prodotto ogg;

            while(rs.next()){
                ogg = new Prodotto();
                ogg.setId(rs.getInt("id"));
                ogg.setNome(rs.getString("nome"));
                ogg.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setId(rs.getInt("disponibilita"));
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
            Utility.msgInf("GEOSTORE", "Errore nel getProdottiViaMateriaWithDB: " + e.getMessage());
        }

        return prodotti;
    }

    @Override
    public Prodotto getProdottoWithDB(Integer id) {
        String sql = "select o.id, o.nome, o.prezzo, o.disponibilita, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o \n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Prodotto prodotto = new Prodotto();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setId(rs.getInt("disponibilita"));
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
            Utility.msgInf("GEOSTORE", "Errore nel getProdottoWithDB: " + e.getMessage());
        }

        return prodotto;
    }

    public Prodotto getProdottoDispWithDB(Integer id) {
        String sql = "select o.id, o.nome, o.prezzo, o.disponibilita, c.nome as categoria_code, m.nome as materia_code, o.quantita_disp \n" +
                "from prodotti o \n" +
                "join Materie m on (o.materia = m.id)\n" +
                "join Categorie c on (o.categoria = c.id)\n" +
                "where o.id = ? AND (o.disponibilita = 1 OR o.disponibilita = 3)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Prodotto prodotto = new Prodotto();
        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setId(rs.getInt("disponibilita"));
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
            Utility.msgInf("GEOSTORE", "Errore nel getProdottoDispWithDB: " + e.getMessage());
        }

        return prodotto;
    }

    @Override
    public int updateProdottoWithDB(Integer id, Prodotto newP) {
        String sql = "UPDATE `prodotti` SET `nome` = ?, `prezzo` = ?, `disponibilita` = ?, `categoria` = ?, `materia` = ?, `quantita_disp` = ? WHERE id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setString(1, newP.getNome());
            preparedStatement.setBigDecimal(2, newP.getPrezzo());
            preparedStatement.setInt(3, newP.getDisponibilita().fromStringToInt(newP.getDisponibilita().getCode()));
            preparedStatement.setInt(4, newP.getCategoria().getId());
            preparedStatement.setInt(5, newP.getMateria().getId());
            preparedStatement.setInt(6, newP.getQuantita_disp());

            preparedStatement.setInt(7, id);

            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgInf("GEOSTORE", "Errore nel updateProdottoWithDB: " + e.getMessage());
        }

        return num;
    }

    public int updateProdottoAfterAccOrdineWithDB(Integer id, Prodotto newP) {
        String sql = "UPDATE `prodotti` SET `disponibilita` = ?, `quantita_disp` = ? WHERE id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //int num = 0;

            preparedStatement.setInt(1, newP.getDisponibilita().getId());
            preparedStatement.setInt(2, newP.getQuantita_disp());

            preparedStatement.setInt(3, id);

            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgInf("GEOSTORE", "Errore nel updateProdottoAfterAccOrdineWithDB: " + e.getMessage());
        }

        return num;
    }

    @Override
    public int deleteProdottoWithDB(Integer id) {
        String sql = "DELETE FROM `prodotti` WHERE id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num = 0;

        try{
            //Connessione al db
            connection = DBConnection.sqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            num = preparedStatement.executeUpdate();
            //chiudi la connessione
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            Utility.msgInf("GEOSTORE", "Errore nel deleteProdottoWithDB: " + e.getMessage());
        }

        return num;
    }

    //metodi override per operazioni CRUD




}
