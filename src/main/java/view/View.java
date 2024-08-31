package src.main.java.view;

import src.main.java.model.*;
import src.main.java.utility.Utility;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class View {

    public int readMenuCliente(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Profilo utente");
        System.out.println("2) Visualizza prodotti");
        System.out.println("3) Ordina prodotto");
        System.out.println("4) Visualizza i tuoi ordini");
        System.out.println("5) Elimina ordine");
        System.out.println("6) Visualizza prodotti per categoria");
        System.out.println("7) Visualizza prodotti per materia");
        System.out.println("8) Visualizza prodotti disponibili");
        System.out.println("9) Visualizza i tuoi ordini totali giornalieri");

        System.out.println("0) ESCI");

        return Utility.insertInt("******");
    }

    public int readMenuAdmin(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Profilo utente");
        System.out.println("2) Visualizza utenti");
        System.out.println("3) Crea/Modifica/Elimina utente");
        System.out.println("4) Visualizza prodotti");
        System.out.println("5) Crea/Modifica/Elimina prodotto");
        System.out.println("6) Ordina prodotto");
        System.out.println("7) Visualizza i tuoi ordini");
        System.out.println("8) Visualizza gli ordini");
        System.out.println("9) Modifica/Elimina ordine");
        System.out.println("10) Visualizza prodotti per categoria");
        System.out.println("11) Visualizza prodotti per materia");
        System.out.println("12) Visualizza prodotti disponibili");
        System.out.println("13) Crea/Modifica/Elimina categoria");
        System.out.println("14) Crea/Modifica/Elimina materia");
        System.out.println("15) Visualizza ordini totali giornalieri");

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
        LocalDateTime currentDateTime = LocalDateTime.now();
        String strDateTime = String.valueOf(currentDateTime);
        strDateTime = strDateTime.substring(0, 19);
        strDateTime = strDateTime.replace("T", " ");

        o.setData_ordine(Timestamp.valueOf(strDateTime));
        Stato stato = new Stato();
        stato.setCode("ELABORAZIONE");
        o.setStato(stato);
        o.setCliente(cliente);
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(Utility.insertString("Inserisci il nome prodotto"));
        o.setProdotto(prodotto);
        o.setQuantita(Utility.insertInt("Inserisci la quantità"));
    }

    public void maskInsertCategoria(Categoria c){
        c.setNome(Utility.insertString("Inserisci il nome categoria"));
        c.setCount();
    }

    public void maskInsertMateria(Materia m){
        m.setNome(Utility.insertString("Inserisci il nome materia"));
        m.setCount();
    }

    public void maskInsertUtente(Cliente c){
        c.setNome(Utility.insertString("Inserisci il nome utente"));
        c.setCognome(Utility.insertString("Inserisci il cognome utente"));
        c.setEmail(Utility.insertString("Inserisci l'email utente"));
        c.setTelefono(Utility.insertString("Inserisci il numero di telefono"));
        c.setIndirizzo(Utility.insertString("Inserisci l'indirizzo"));
        c.setCount();

        if(c instanceof Amministratore){
            Amministratore a = (Amministratore) c;
            a.setCodeAdmin(Utility.insertString("Inserisci il codice amministratore"));
        }
    }

    public void maskInsertProdotto(Prodotto p){
        p.setNome(Utility.insertString("Inserisci il nome prodotto"));
        p.setPrezzo(Utility.insertBigDecimal("Inserisci il prezzo unitario prodotto"));
        p.setQuantita_disp(Utility.insertInt("Inserisci la quantità disponibile prodotto"));
        Categoria c = new Categoria();
        c.setNome(Utility.insertString("Inserisci il nome categoria appartenente"));
        p.setCategoria(c);
        Materia m = new Materia();
        m.setNome(Utility.insertString("Inserisci il nome materia appartenente"));
        p.setMateria(m);
        String dispn = Utility.insertString("Inserisci la disponibilità prodotto");
        Disponibilita disp = new Disponibilita();

        if(dispn.equalsIgnoreCase("DISPONIBILE")){
            disp.setCode("DISPONIBILE");
            p.setDisponibilita(disp);
        }
        else if(dispn.equalsIgnoreCase("ESAURITO")){
            disp.setCode("ESAURITO");
            p.setDisponibilita(disp);
        }
        else if(dispn.equalsIgnoreCase("ESAURIMENTO")){
            disp.setCode("ESAURIMENTO");
            p.setDisponibilita(disp);
        }
        else if(dispn.equalsIgnoreCase("ARRIVO")){
            disp.setCode("ARRIVO");
            p.setDisponibilita(disp);
        }
        else{
            disp.setCode("N/A");
            p.setDisponibilita(disp);
        }

        p.setCount();
    }

    public Cliente maskUpdateUtente(Cliente cOld, Cliente cNew){
        String nome = Utility.insertString("Inserisci il nome da " + cOld.getNome() + " a: ");
        String cognome = Utility.insertString("Inserisci il cognome da " + cOld.getCognome() + " a: ");
        String email = Utility.insertString("Inserisci l'email da " + cOld.getEmail() + " a: ");
        String telefono = Utility.insertString("Inserisci il numero di telefono da " + cOld.getTelefono() + " a: ");
        String indirizzo = Utility.insertString("Inserisci l'indirizzo da " + cOld.getIndirizzo() + " a: ");
        cNew.setId(cOld.getId());

        if(!nome.isEmpty()){
            cNew.setNome(nome);
        }
        else{
            cNew.setNome(cOld.getNome());
        }

        if(!cognome.isEmpty()){
            cNew.setCognome(cognome);
        }
        else{
            cNew.setCognome(cOld.getCognome());
        }

        if(!email.isEmpty()){
            cNew.setEmail(email);
        }
        else{
            cNew.setEmail(cOld.getEmail());
        }

        if(!telefono.isEmpty()){
            cNew.setTelefono(telefono);
        }
        else{
            cNew.setTelefono(cOld.getTelefono());
        }

        if(!indirizzo.isEmpty()){
            cNew.setIndirizzo(indirizzo);
        }
        else{
            cNew.setIndirizzo(cOld.getIndirizzo());
        }

        if(cOld instanceof Amministratore){
            Amministratore aOld = (Amministratore) cOld;
            Amministratore aNew = (Amministratore) cNew;

            String codeAdmin = Utility.insertString("Inserisci il codice amministratore da " + aOld.getCodeAdmin() + " a: ");


            if(!codeAdmin.isEmpty()) {
                aNew.setCodeAdmin(codeAdmin);
            }
            else {
                aNew.setCodeAdmin(aOld.getCodeAdmin());
            }
        }
        Cliente updateCl = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updateCl = cNew;
        }
        else{
            updateCl = cOld;
        }

        return updateCl;
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

    public Materia maskUpdateMateria(Materia mOld, Materia mNew){
        String nome = Utility.insertString("Inserisci il nome da " + mOld.getNome() + " a: ");
        mNew.setId(mOld.getId());

        if(!nome.isEmpty()){
            mNew.setNome(nome);
        }
        else{
            mNew.setNome(mOld.getNome());
        }
        Materia updateMat = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updateMat = mNew;
        }
        else{
            updateMat = mOld;
        }

        return updateMat;
    }

    public Prodotto maskUpdateProdotto(Prodotto pOld, Prodotto pNew){
        String nome = Utility.insertString("Inserisci il nome da " + pOld.getNome() + " a: ");
        String disponibilita = Utility.insertString("Inserisci la disponibilità da " + pOld.getDisponibilita().fromIntToString(pOld.getDisponibilita().getId()) + " a: ");
        String categoria = Utility.insertString("Inserisci la categoria da " + pOld.getCategoria().getNome() + " a: ");
        String materia = Utility.insertString("Inserisci la materia da " + pOld.getMateria().getNome() + " a: ");
        pNew.setId(pOld.getId());

        if(!nome.isEmpty()){
            pNew.setNome(nome);
        }
        else{
            pNew.setNome(pOld.getNome());
        }

        boolean flag;
        do {
            flag = false;
            String prezzo = Utility.insertString("Inserisci il prezzo unitario prodotto da " + pOld.getPrezzo() + " a: ");
            if(!prezzo.isEmpty()) {
                try {
                    pNew.setPrezzo(new BigDecimal(prezzo));
                }
                catch(NumberFormatException e){
                    Utility.msgInf("GEOSTORE","ATTENZIONE: inserisci un numero valido");
                    flag=true;
                }
                catch(Exception e) {
                    Utility.msgInf("GEOSTORE","ERRORE");
                    flag=true;
                }
            }
            else {
                pNew.setPrezzo(pOld.getPrezzo());
            }
        }while(flag);

        do {
            flag = false;
            String quantita_disp = Utility.insertString("Inserisci la quantità disponibile da " + pOld.getQuantita_disp() + " a: ");
            if(!quantita_disp.isEmpty()) {
                try {
                    pNew.setQuantita_disp(Integer.parseInt(quantita_disp));
                }
                catch(NumberFormatException e){
                    Utility.msgInf("GEOSTORE","ATTENZIONE: inserisci un numero valido");
                    flag=true;
                }
                catch(Exception e) {
                    Utility.msgInf("GEOSTORE","ERRORE");
                    flag=true;
                }
            }
            else {
                pNew.setQuantita_disp(pOld.getQuantita_disp());
            }
        }while(flag);


        Disponibilita disp = new Disponibilita();

        if(!disponibilita.isEmpty()){
            if(disponibilita.equalsIgnoreCase("DISPONIBILE")){
                disp.setCode("DISPONIBILE");
                pNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ESAURITO")){
                disp.setCode("ESAURITO");
                pNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ESAURIMENTO")){
                disp.setCode("ESAURIMENTO");
                pNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("ARRIVO")){
                disp.setCode("ARRIVO");
                pNew.setDisponibilita(disp);
            }
            else if(disponibilita.equalsIgnoreCase("N/A")){
                disp.setCode("N/A");
                pNew.setDisponibilita(disp);
            }
            else{
                disp.setCode(pOld.getDisponibilita().fromIntToString(pOld.getDisponibilita().getId()));
                pNew.setDisponibilita(disp);
            }
        }
        else{
            disp.setCode(pOld.getDisponibilita().fromIntToString(pOld.getDisponibilita().getId()));
            pNew.setDisponibilita(disp);
        }

        if(!categoria.isEmpty()){
            Categoria c = new Categoria();
            c.setNome(categoria);
            pNew.setCategoria(c);
        }
        else{
            pNew.setCategoria(pOld.getCategoria());
        }

        if(!materia.isEmpty()){
            Materia m = new Materia();
            m.setNome(materia);
            pNew.setMateria(m);
        }
        else{
            pNew.setMateria(pOld.getMateria());
        }

        Prodotto updatePr = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updatePr = pNew;
        }
        else{
            updatePr = pOld;
        }

        return updatePr;
    }

    public Ordine maskUpdateOrdine(Ordine oOld, Ordine oNew){
        BigDecimal prezzo = Utility.insertBigDecimal("Inserisci il prezzo unitario da " + oOld.getPrezzo_unitario() + " a: ");
        Integer quantita_ord = Utility.insertInt("Inserisci la quantità ordinata da " + oOld.getQuantita() + " a: ");
        String stato = Utility.insertString("Inserisci lo stato da " + oOld.getStato().fromIntToString(oOld.getStato().getId()) + " a: ");
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
                oNew.setStato(st);
            }
            else if(stato.equalsIgnoreCase("ACCETTATO")){
                st.setCode("ACCETTATO");
                oNew.setStato(st);
            }
            else if(stato.equalsIgnoreCase("RIFIUTATO")){
                st.setCode("RIFIUTATO");
                oNew.setStato(st);
            }
            else{
                st.setCode(oOld.getStato().fromIntToString(oOld.getStato().getId()));
                oNew.setStato(st);
            }
        }
        else{
            st.setCode(oOld.getStato().fromIntToString(oOld.getStato().getId()));
            oNew.setStato(st);
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
        if(!categorie.isEmpty()){
            System.out.println("***CATEGORIE***\n");

            for(Categoria categoria : categorie.values()){
                System.out.println("ID: " + categoria.getId() + ", \nNome: " + categoria.getNome());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUNA CATEGORIA PRESENTE***\n");
        }

    }

    public void printProdotti(HashMap<Integer, Prodotto> prodotti){
        if(!prodotti.isEmpty()){
            System.out.println("***PRODOTTI PRESENTI NEL GEOSTORE***\n");

            for(Prodotto prodotto : prodotti.values()){
                System.out.println("ID: " + prodotto.getId() + ", \nNome: " + prodotto.getNome() + ", \nPrezzo: " + prodotto.getPrezzo()+ ", \nDisponibilità: " + prodotto.getDisponibilita().fromIntToString(prodotto.getDisponibilita().getId()) + ", \nCategoria: " + prodotto.getCategoria().getNome() + ", \nMateria: " + prodotto.getMateria().getNome() + ", \nQuantità disponibile: " + prodotto.getQuantita_disp());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUN PRODOTTO PRESENTE NEL GEOSTORE***\n");
        }

    }

    public void printOrdini(HashMap<Integer, Ordine> ordini){
        if(!ordini.isEmpty()){
            System.out.println("***ORDINI EFFETTUATI NEL GEOSTORE***\n");

            for(Ordine ordine : ordini.values()){
                System.out.println("ID: " + ordine.getId() + ", \nNome cliente: " + ordine.getCliente().getNome() + ", \nCognome cliente: " + ordine.getCliente().getCognome() + ", \nNome prodotto: " + ordine.getProdotto().getNome() + ", \nData ordine: " + ordine.getData_ordine() + ", \nQuantità ordinata: " + ordine.getQuantita() + ", \nPrezzo unitario: " + ordine.getPrezzo_unitario()+ ", \nStato ordine: " + ordine.getStato().fromIntToString(ordine.getStato().getId()));
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUN ORDINE EFFETTUATO NEL GEOSTORE***\n");

        }
    }

    public void printOrdiniTotGior(Ordine ordine){
        if(ordine != null && ordine.getPrezzo_unitario() != null){
            System.out.println("***ORDINI TOTALI EFFETTUATI NEL GIORNO " + ordine.getData_ordine().toString().substring(0, 10) + " SU GEOSTORE***\n");

            System.out.println("Nome cliente: " + ordine.getCliente().getNome() + "\nCognome cliente: " + ordine.getCliente().getCognome() + ", \nData ordine totale: " + ordine.getData_ordine().toString().substring(0, 10) + ", \nPrezzo totale speso: " + ordine.getPrezzo_unitario());
        }
        else{
            System.out.println("***NESSUN ORDINE TOTALE PRESENTE NEL GIORNO PRESTABILITO***\n");
        }
    }

    public void printCategoria(Categoria categoria){
        if(categoria != null && categoria.getNome() != null){
            System.out.println("ID: " + categoria.getId() + ", \nNome: " + categoria.getNome());
        }
        else{
            System.out.println("***NESSUNA CATEGORIA***");
        }

    }

    public void printUtente(Cliente cliente){
        if(cliente != null && cliente.getNome() != null){
            System.out.println("***PROFILO UTENTE GEOSTORE***\n");
            System.out.println("ID: " + cliente.getId() + ", \nNome: " + cliente.getNome() + ", \nCognome: " + cliente.getCognome()+ ", \nEmail: " + cliente.getEmail()+ ", \nTelefono: " + cliente.getTelefono()+ ", \nIndirizzo: " + cliente.getIndirizzo());
        }
        else{
            System.out.println("***NESSUN PROFILO UTENTE GEOSTORE***\n");
        }
    }

    public void printUtenti(HashMap<Integer, Cliente> clienti){
        if(!clienti.isEmpty()){
            System.out.println("***UTENTI SU GEOSTORE***\n");

            for(Cliente cliente : clienti.values()){
                System.out.println("ID: " + cliente.getId() + ", \nNome: " + cliente.getNome() + ", \nCognome: " + cliente.getCognome()+ ", \nEmail: " + cliente.getEmail()+ ", \nTelefono: " + cliente.getTelefono()+ ", \nIndirizzo: " + cliente.getIndirizzo());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUN UTENTE PRESENTE SU GEOSTORE***\n");
        }
    }
}
