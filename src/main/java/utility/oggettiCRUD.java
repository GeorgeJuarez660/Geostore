package src.main.java.utility;

import src.main.java.model.Categoria;

import java.util.HashMap;

public interface oggettiCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe CategoriaRepository

    //metodi override per operazioni CRUD con database
    public void insertOggettoWithDB(Integer id, Categoria c);
    public HashMap<Integer, Categoria> getOggettiWithDB();
    public Categoria getOggettoWithDB(String nome);
    public void updateOggettoWithDB(Integer id, Categoria newC);
    public void deleteOggettoWithDB(Integer id);

}
