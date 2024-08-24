package src.main.java.utility;

import src.main.java.model.Oggetto;
import src.main.java.model.Ordine;

import java.util.HashMap;

public interface ordiniCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe OrdineRepository

    //metodi override per operazioni CRUD con database
    public int insertOrdineWithDB(Integer id, Ordine o);
    public HashMap<Integer, Ordine> getOrdiniWithDB();
    public Ordine getOrdineWithDB(String nome);
    public void updateOrdineWithDB(Integer id, Ordine newO);
    public void deleteOrdineWithDB(Integer id);

}
