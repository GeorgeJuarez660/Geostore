package src.main.java.view;

import src.main.java.model.*;
import src.main.java.utility.Utility;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class View {

    public int readMenuCliente(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Profilo utente");
        System.out.println("2) Visualizza oggetti");
        System.out.println("3) Ordina oggetto");
        System.out.println("4) Visualizza i tuoi ordini");
        System.out.println("5) Elimina ordine");
        System.out.println("6) Visualizza oggetti per categoria");
        System.out.println("7) Visualizza oggetti per materia");
        System.out.println("8) Visualizza oggetti disponibili");
        System.out.println("9) Visualizza i tuoi ordini totali giornalieri");

        System.out.println("0) ESCI");

        return Utility.insertInt("******");
    }

    public int readMenuAdmin(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Profilo utente");
        System.out.println("2) Visualizza utenti");
        System.out.println("3) Visualizza oggetti");
        System.out.println("4) Crea/Modifica/Elimina oggetto");
        System.out.println("5) Ordina oggetto");
        System.out.println("6) Visualizza i tuoi ordini");
        System.out.println("7) Visualizza gli ordini");
        System.out.println("8) Modifica/Elimina ordine");
        System.out.println("9) Visualizza oggetti per categoria");
        System.out.println("10) Visualizza oggetti per materia");
        System.out.println("11) Visualizza oggetti disponibili");
        System.out.println("12) Crea/Modifica/Elimina categoria");
        System.out.println("13) Crea/Modifica/Elimina materia");
        System.out.println("14) Visualizza ordini totali giornalieri");

        System.out.println("0) ESCI");

        return Utility.insertInt("******");
    }

    public int readAdminOrUserMenu(){
        System.out.println("***BENVENUTO SU GEOSTORE***\n");
        System.out.println("***SEI UN CLIENTE O ADMIN?***");
        System.out.println("1) Cliente");
        System.out.println("2) Admin");

        return Utility.insertInt("******");
    }

    public void maskCheckUser(Cliente c){
        String email = Utility.insertString("Inserisci l'email cliente:");

        if(!email.isEmpty()){
            c.setEmail(email);
        }
        else{
            c.setEmail(null);
        }

        if(c instanceof Amministratore){
            Amministratore aC = (Amministratore) c;
            String codeAdmin = Utility.insertString("Inserisci il codice amministratore:");

            if(!codeAdmin.isEmpty()){
                aC.setCodeAdmin(codeAdmin);
            }
            else{
                aC.setCodeAdmin(null);
            }
        }
    }

    public void maskObjViaCat(Categoria c){
        String nome = Utility.insertString("Inserisci il nome categoria:");

        if(!nome.isEmpty()){
            c.setNome(nome);
        }
        else{
            c.setNome(null);
        }

    }

    public void maskObjViaMat(Materia m){
        String nome = Utility.insertString("Inserisci il nome materia:");

        if(!nome.isEmpty()){
            m.setNome(nome);
        }
        else{
            m.setNome(null);
        }

    }

    public void maskInsertOrdine(Ordine o, Cliente cliente){
        LocalDateTime localDateTime = LocalDateTime.now();
        // Estrazione di LocalDate da LocalDateTime
        LocalDate localDate = localDateTime.toLocalDate();

        o.setData_ordine(Date.valueOf(localDate));
        Stato stato = new Stato();
        stato.setId(1);
        o.setStato(stato);
        o.setCliente(cliente);
        Oggetto oggetto = new Oggetto();
        oggetto.setNome(Utility.insertString("Inserisci il nome oggetto"));
        o.setOggetto(oggetto);
        o.setQuantita(Utility.insertInt("Inserisci la quantità"));
    }

    public void maskInsertCategoria(Categoria c){
        c.setNome(Utility.insertString("Inserisci il nome categoria"));
        c.setCount();
    }

    public Categoria maskUpdateCategoria(Categoria cOld, Categoria cNew){
        String nome = Utility.insertString("Inserisci il nome da " + cOld.getNome() + " a: ");
        cNew.setId(cOld.getId());

        if(!nome.isEmpty()){
            cNew.setNome(nome);
        }
        else{
            cNew.setNome(cOld.getNome());
        }
        Categoria updateCat = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updateCat = cNew;
        }
        else{
            updateCat = cOld;
        }

        return updateCat;
    }

    public Oggetto maskUpdateOggetto(Oggetto oOld, Oggetto oNew){
        String nome = Utility.insertString("Inserisci il nome da " + oOld.getNome() + " a: ");
        BigDecimal prezzo = Utility.insertBigDecimal("Inserisci il prezzo da " + oOld.getPrezzo() + " a: ");
        Integer quantita_disp = Utility.insertInt("Inserisci la quantità disponibile da " + oOld.getQuantita_disp() + " a: ");
        String disponibilita = Utility.insertString("Inserisci la disponibilità da " + oOld.getDisponibilita().getCode() + " a: ");
        String categoria = Utility.insertString("Inserisci la categoria da " + oOld.getCategoria().getNome() + " a: ");
        String materia = Utility.insertString("Inserisci la materia da " + oOld.getMateria().getNome() + " a: ");
        oNew.setId(oOld.getId());

        if(!nome.isEmpty()){
            oNew.setNome(nome);
        }
        else{
            oNew.setNome(oOld.getNome());
        }

        if(prezzo != null){
            oNew.setPrezzo(prezzo);
        }
        else{
            oNew.setPrezzo(oOld.getPrezzo());
        }

        if(quantita_disp != null){
            oNew.setQuantita_disp(quantita_disp);
        }
        else{
            oNew.setQuantita_disp(oOld.getQuantita_disp());
        }

        Disponibilita disp = new Disponibilita();

        if(!disponibilita.isEmpty()){
            if(disponibilita.equalsIgnoreCase("DISPONIBILE")){
                disp.setId(1);
                disp.setCode("DISPONIBILE");
                oNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ESAURITO")){
                disp.setId(4);
                disp.setCode("ESAURITO");
                oNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ESAURIMENTO")){
                disp.setId(3);
                disp.setCode("ESAURIMENTO");
                oNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ARRIVO")){
                disp.setId(2);
                disp.setCode("ARRIVO");
                oNew.setDisponibilita(disp);
            }
            else{
                oNew.setDisponibilita(oOld.getDisponibilita());
            }
        }
        else{
            oNew.setDisponibilita(oOld.getDisponibilita());
        }

       /* Disponibilita disp = new Disponibilita();

        if(!disponibilita.isEmpty()){
            if(disponibilita.equalsIgnoreCase("DISPONIBILE")){
                disp.setId(1);
                disp.setCode("DISPONIBILE");
                oNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ESAURITO")){
                disp.setId(4);
                disp.setCode("ESAURITO");
                oNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ESAURIMENTO")){
                disp.setId(3);
                disp.setCode("ESAURIMENTO");
                oNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ARRIVO")){
                disp.setId(2);
                disp.setCode("ARRIVO");
                oNew.setDisponibilita(disp);
            }
            else{
                oNew.setDisponibilita(oOld.getDisponibilita());
            }
        }
        else{
            oNew.setDisponibilita(oOld.getDisponibilita());
        }*/

        Oggetto updateOgg = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updateOgg = oNew;
        }
        else{
            updateOgg = oOld;
        }

        return updateOgg;
    }

    public Ordine maskUpdateOrdine(Ordine oOld, Ordine oNew){
        BigDecimal prezzo = Utility.insertBigDecimal("Inserisci il prezzo unitario da " + oOld.getPrezzo_unitario() + " a: ");
        Integer quantita_ord = Utility.insertInt("Inserisci la quantità ordinata da " + oOld.getQuantita() + " a: ");
        String stato = Utility.insertString("Inserisci lo stato da " + oOld.getStato().getCode() + " a: ");
        oNew.setId(oOld.getId());

        if(prezzo != null){
            oNew.setPrezzo_unitario(prezzo);
        }
        else{
            oNew.setPrezzo_unitario(oOld.getPrezzo_unitario());
        }

        if(quantita_ord != null){
            oNew.setQuantita(quantita_ord);
        }
        else{
            oNew.setQuantita(oOld.getQuantita());
        }

        Stato st = new Stato();
        if(!stato.isEmpty()){
            if(stato.equalsIgnoreCase("ELABORAZIONE")){
                st.setCode("ELABORAZIONE");
                st.setId(1);
                oNew.setStato(st);
            }
            else if(stato.equalsIgnoreCase("ACCETTATO")){
                st.setCode("ACCETTATO");
                st.setId(2);
                oNew.setStato(st);
            }
            else if(stato.equalsIgnoreCase("RIFIUTATO")){
                st.setCode("RIFIUTATO");
                st.setId(3);
                oNew.setStato(st);
            }
            else{
                oNew.setStato(oOld.getStato());
            }
        }
        else{
            oNew.setStato(oOld.getStato());
        }

        Ordine updateOrd = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updateOrd = oNew;
        }
        else{
            updateOrd = oOld;
        }

        return updateOrd;
    }

    public void printCategorie(HashMap<Integer, Categoria> categorie){
        System.out.println("***CATEGORIE***\n\n");

        for(Categoria categoria : categorie.values()){
            System.out.println("ID: " + categoria.getId() + ", \nNome: " + categoria.getNome());
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void printOggetti(HashMap<Integer, Oggetto> oggetti){
        System.out.println("***PRODOTTI PRESENTI NEL GEOSTORE***\n\n");

        for(Oggetto oggetto : oggetti.values()){
            System.out.println("ID: " + oggetto.getId() + ", \nNome: " + oggetto.getNome() + ", \nPrezzo: " + oggetto.getPrezzo()+ ", \nDisponibilità: " + oggetto.getDisponibilita().getCode() + ", \nCategoria: " + oggetto.getCategoria().getNome() + ", \nMateria: " + oggetto.getMateria().getNome() + ", \nQuantità disponibile: " + oggetto.getQuantita_disp());
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void printOrdini(HashMap<Integer, Ordine> ordini){
        System.out.println("***ORDINI EFFETTUATI NEL GEOSTORE***\n\n");

        for(Ordine ordine : ordini.values()){
            System.out.println("ID: " + ordine.getId() + ", \nNome cliente: " + ordine.getCliente().getNome() + ", \nCognome cliente: " + ordine.getCliente().getCognome() + ", \nNome prodotto: " + ordine.getOggetto().getNome() + ", \nData ordine: " + ordine.getData_ordine() + ", \nQuantità ordinata: " + ordine.getQuantita() + ", \nPrezzo unitario: " + ordine.getPrezzo_unitario()+ ", \nStato ordine: " + ordine.getStato().getCode());
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void printOrdiniTotGior(HashMap<Integer, Ordine> ordini){
        System.out.println("***ORDINI TOTALI GIORNALIERI EFFETTUATI NEL GEOSTORE***\n\n");

        for(Ordine ordine : ordini.values()){
            //System.out.println("ID: " + oggetto.getId() + ", \nNome: " + oggetto.getNome() + ", \nPrezzo: " + oggetto.getPrezzo()+ ", \nDisponibilità: " + oggetto.getDisponibilita().getCode() + ", \nCategoria: " + oggetto.getCategoria().getNome() + ", \nMateria: " + oggetto.getMateria().getNome() + ", \nQuantità disponibile: " + oggetto.getQuantita_disp());
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void printCategoria(Categoria categoria){
        System.out.println("ID: " + categoria.getId() + ", \nNome: " + categoria.getNome());
    }

    public void printUtente(Cliente cliente){
        System.out.println("***PROFILO UTENTE GEOSTORE***\n");
        System.out.println("ID: " + cliente.getId() + ", \nNome: " + cliente.getNome() + ", \nCognome: " + cliente.getCognome()+ ", \nEmail: " + cliente.getEmail()+ ", \nTelefono: " + cliente.getTelefono()+ ", \nIndirizzo: " + cliente.getIndirizzo());
    }

    public void printUtenti(HashMap<Integer, Cliente> clienti){
        System.out.println("***UTENTI SU GEOSTORE***\n\n");

        for(Cliente cliente : clienti.values()){
            System.out.println("ID: " + cliente.getId() + ", \nNome: " + cliente.getNome() + ", \nCognome: " + cliente.getCognome()+ ", \nEmail: " + cliente.getEmail()+ ", \nTelefono: " + cliente.getTelefono()+ ", \nIndirizzo: " + cliente.getIndirizzo());
            System.out.println("-----------------------------------------------------------");
        }
    }
}
