package src.main.java.utility;

import src.main.java.model.Cliente;
import src.main.java.model.Utente;

import java.util.HashMap;

public interface utentiCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe UtenteRepository

    //metodi override per operazioni CRUD con database
    public int insertUtenteWithDB(Integer id, Utente u);
    public HashMap<Integer, Utente> getUtentiWithDB();
    public Utente getUtenteWithDB(Integer id);
    public int updateUtenteWithDB(Integer id, Utente newU);
    public int deleteUtenteWithDB(Integer id);

}
