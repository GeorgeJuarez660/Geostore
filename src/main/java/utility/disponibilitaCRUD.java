package src.main.java.utility;

import src.main.java.model.Disponibilita;
import src.main.java.model.Ordine;

import java.util.HashMap;

public interface disponibilitaCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe DisponibilitaRepository

    //metodi override per operazioni CRUD con database
    public void insertDisponibilitaWithDB(Integer id, Disponibilita d);
    public HashMap<Integer, Disponibilita> getDisponibilitaWithDB();
    public Disponibilita getDisponibilitaWithDB(Integer id);
    public void updateDisponibilitaWithDB(Integer id, Disponibilita newD);
    public void deleteDisponibilitaWithDB(Integer id);

}
