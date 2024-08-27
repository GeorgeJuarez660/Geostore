package src.main.java.utility;

import src.main.java.model.Prodotto;

import java.util.HashMap;

public interface prodottiCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe OggettoRepository

    //metodi override per operazioni CRUD con database
    public void insertProdottoWithDB(Integer id, Prodotto p);
    public HashMap<Integer, Prodotto> getProdottiWithDB();
    public Prodotto getProdottoWithDB(String nome);
    public void updateProdottoWithDB(Integer id, Prodotto newP);
    public void deleteProdottoWithDB(Integer id);

}
