package src.main.java.utility;

import src.main.java.model.Cliente;
import src.main.java.model.Utente;

import java.util.HashMap;

public interface utentiCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe ClienteRepository

    //metodi override per operazioni CRUD con database
    public int insertUtenteWithDB(Integer id, Utente u);
    public HashMap<Integer, Cliente> getClientiWithDB();
    public Cliente getClienteWithDB(String nome);
    public int updateClienteWithDB(Integer id, Cliente newC);
    public int deleteClienteWithDB(Integer id);

}
