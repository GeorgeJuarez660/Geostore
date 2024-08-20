package src.main.java.utility;

import src.main.java.model.Categoria;
import src.main.java.model.Oggetto;

import java.util.HashMap;

public interface oggettiCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe OggettoRepository

    //metodi override per operazioni CRUD con database
    public void insertOggettoWithDB(Integer id, Oggetto o);
    public HashMap<Integer, Oggetto> getOggettiWithDB();
    public Oggetto getOggettoWithDB(String nome);
    public void updateOggettoWithDB(Integer id, Oggetto newO);
    public void deleteOggettoWithDB(Integer id);

}
