package src.main.java.view;

import src.main.java.model.*;
import src.main.java.utility.Utility;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

public class View {

    ProdottoRepository pr = new ProdottoRepository();
    CategoriaRepository car = new CategoriaRepository();
    MateriaRepository mr = new MateriaRepository();
    DisponibilitaRepository dr = new DisponibilitaRepository();
    StatusRepository sr = new StatusRepository();

    public int readMenuCliente(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Profilo utente");
        System.out.println("2) Modifica profilo utente");
        System.out.println("3) Visualizza prodotti");
        System.out.println("4) Ordina prodotto");
        System.out.println("5) Visualizza i tuoi ordini");
        System.out.println("6) Elimina ordine");
        System.out.println("7) Visualizza prodotti per categoria");
        System.out.println("8) Visualizza prodotti per materia");
        System.out.println("9) Visualizza prodotti disponibili");
        System.out.println("10) Visualizza i tuoi ordini totali giornalieri");

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
        System.out.println("7) Visualizza gli ordini");
        System.out.println("8) Modifica/Elimina ordine");
        System.out.println("9) Visualizza prodotti per categoria");
        System.out.println("10) Visualizza prodotti per materia");
        System.out.println("11) Visualizza prodotti disponibili");
        System.out.println("12) Crea/Modifica/Elimina categoria");
        System.out.println("13) Crea/Modifica/Elimina materia");
        System.out.println("14) Visualizza ordini totali giornalieri");

        System.out.println("0) ESCI");

        return Utility.insertInt("******");
    }

    public int readAdminOrUserMenu(){
        System.out.println("***ACCEDI COME CLIENTE/ADMIN***");
        System.out.println("1) Cliente");
        System.out.println("2) Admin");

        return Utility.insertInt("******");
    }

    public int registerOrLogin(){
        System.out.println("***BENVENUTO SU GEOSTORE***\n");
        System.out.println("1) Registrati");
        System.out.println("2) Accedi");
        System.out.println("0) ESCI");


        return Utility.insertInt("******");
    }

    public void readDisponibilita(){
        System.out.println("\n");

        System.out.println("1) DISPONIBILE");
        System.out.println("2) ARRIVO");
        System.out.println("3) ESAURIMENTO");
        System.out.println("4) ESAURITO");
        System.out.println("5) N/A");

        System.out.println("\n");
    }
    public void maskCheckUser(Utente u){

        if(u instanceof Amministratore){
            Amministratore aU = (Amministratore) u;

            String email = Utility.insertString("Inserisci l'email cliente:");
            String password = Utility.insertString("Inserisci la password cliente:");
            String codeAdmin = Utility.insertString("Inserisci il codice amministratore:");

            if(!email.isEmpty()){
                aU.setEmail(email);
            }
            else{
                aU.setEmail(null);
            }

            if(!password.isEmpty()){
                aU.setPassword(password);
            }
            else{
                aU.setPassword(null);
            }

            if(!codeAdmin.isEmpty()){
                aU.setCodeAdmin(codeAdmin);
            }
            else{
                aU.setCodeAdmin(null);
            }
        }
        else if(u instanceof Cliente){
            Cliente cU = (Cliente) u;

            String email = Utility.insertString("Inserisci l'email cliente:");
            String password = Utility.insertString("Inserisci la password cliente:");

            if(!email.isEmpty()){
                cU.setEmail(email);
            }
            else{
                cU.setEmail(null);
            }

            if(!password.isEmpty()){
                cU.setPassword(password);
            }
            else{
                cU.setPassword(null);
            }
        }
    }

    public void maskObjViaCat(Categoria c){
        Integer id = Utility.insertInt("Inserisci l'id categoria:");

        if(id != null){
            c.setId(id);
        }
        else{
            c.setId(0);
        }

    }

    public void maskObjViaMat(Materia m){
        Integer id = Utility.insertInt("Inserisci l'id materia:");

        if(id != null){
            m.setId(id);
        }
        else{
            m.setId(0);
        }

    }

    public void maskInsertOrdine(Ordine o, Utente utente){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String strDateTime = String.valueOf(currentDateTime);
        strDateTime = strDateTime.substring(0, 19);
        strDateTime = strDateTime.replace("T", " ");

        o.setData_ordine(Timestamp.valueOf(strDateTime));
        Stato stato = new Stato();
        stato.setId(1);
        o.setStato(stato);
        o.setUtente(utente);

        Prodotto prodotto = new Prodotto();
        prodotto.setId(Utility.insertInt("Inserisci l'id prodotto"));
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

    public void maskInsertUtente(Utente u){
        u.setNome(Utility.insertString("Inserisci il nome utente"));
        u.setCognome(Utility.insertString("Inserisci il cognome utente"));
        u.setTelefono(Utility.insertString("Inserisci il numero di telefono"));
        u.setIndirizzo(Utility.insertString("Inserisci l'indirizzo"));
        u.setCount();

        if(u instanceof Amministratore){
            Amministratore a = (Amministratore) u;
            a.setEmail(Utility.insertString("Inserisci l'email utente"));
            a.setPassword(Utility.insertString("Inserisci la password utente"));
            a.setPortafoglio(new BigDecimal(50));
            a.setCodeAdmin(Utility.insertString("Inserisci il codice amministratore"));
        }
        else if(u instanceof Cliente){
            Cliente c = (Cliente) u;
            c.setEmail(Utility.insertString("Inserisci l'email utente"));
            c.setPassword(Utility.insertString("Inserisci la password utente"));
            c.setPortafoglio(new BigDecimal(50));
        }
    }

    public void maskInsertProdotto(Prodotto p){
        p.setNome(Utility.insertString("Inserisci il nome prodotto"));
        p.setPrezzo(Utility.insertBigDecimal("Inserisci il prezzo unitario prodotto"));
        p.setQuantita_disp(Utility.insertInt("Inserisci la quantità disponibile prodotto"));

        Categoria c = new Categoria();
        printCategorie(car.getCategorieWithDB());
        c.setId(Utility.insertInt("\nInserisci l'id categoria appartenente"));
        p.setCategoria(c);

        Materia m = new Materia();
        printMaterie(mr.getMaterieWithDB());
        m.setId(Utility.insertInt("\nInserisci l'id materia appartenente"));
        p.setMateria(m);

        Disponibilita d = new Disponibilita();
        printDisponibilita(dr.getDisponibilitaWithDB());
        d.setId(Utility.insertInt("\nInserisci l'id disponibilita prodotto"));
        p.setDisponibilita(d);

        p.setCount();
    }

    public Utente maskUpdateUtente(Utente uOld, Utente uNew){
        String nome = Utility.insertString("Inserisci il nome da " + uOld.getNome() + " a: ");
        String cognome = Utility.insertString("Inserisci il cognome da " + uOld.getCognome() + " a: ");
        String telefono = Utility.insertString("Inserisci il numero di telefono da " + uOld.getTelefono() + " a: ");
        String indirizzo = Utility.insertString("Inserisci l'indirizzo da " + uOld.getIndirizzo() + " a: ");
        uNew.setId(uOld.getId());

        //TODO: da continuare...
        if(!nome.isEmpty()){
            uNew.setNome(nome);
        }
        else{
            uNew.setNome(uOld.getNome());
        }

        if(!cognome.isEmpty()){
            uNew.setCognome(cognome);
        }
        else{
            uNew.setCognome(uOld.getCognome());
        }

        if(!telefono.isEmpty()){
            uNew.setTelefono(telefono);
        }
        else{
            uNew.setTelefono(uOld.getTelefono());
        }

        if(!indirizzo.isEmpty()){
            uNew.setIndirizzo(indirizzo);
        }
        else{
            uNew.setIndirizzo(uOld.getIndirizzo());
        }

        if(uOld instanceof Amministratore){
            Amministratore aOld = (Amministratore) uOld;
            Amministratore aNew = (Amministratore) uNew;

            String email = Utility.insertString("Inserisci l'email da " + aOld.getEmail() + " a: ");
            String password = Utility.insertString("Inserisci la password da " + aOld.getPassword() + " a: ");
            String codeAdmin = Utility.insertString("Inserisci il codice amministratore da " + aOld.getCodeAdmin() + " a: ");

            if(!email.isEmpty()){
                aNew.setEmail(email);
            }
            else{
                aNew.setEmail(aOld.getEmail());
            }

            if(!password.isEmpty()){
                aNew.setPassword(password);
            }
            else{
                aNew.setPassword(aOld.getPassword());
            }

            if(!codeAdmin.isEmpty()) {
                aNew.setCodeAdmin(codeAdmin);
            }
            else {
                aNew.setCodeAdmin(aOld.getCodeAdmin());
            }
        }
        else if(uOld instanceof Cliente){
            Cliente cOld = (Cliente) uOld;
            Cliente cNew = (Cliente) uNew;

            String email = Utility.insertString("Inserisci l'email da " + cOld.getEmail() + " a: ");
            String password = Utility.insertString("Inserisci la password da " + cOld.getPassword() + " a: ");

            if(!email.isEmpty()){
                cNew.setEmail(email);
            }
            else{
                cNew.setEmail(cOld.getEmail());
            }

            if(!password.isEmpty()){
                cNew.setPassword(password);
            }
            else{
                cNew.setPassword(cOld.getPassword());
            }
        }

        Utente updateUt = null;

        if(Utility.insertString("Vuoi apportare modifiche?").equalsIgnoreCase("s")){
            updateUt = uNew;
        }
        else{
            updateUt = uOld;
        }

        return updateUt;
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

        pNew.setId(pOld.getId());

        if(!nome.isEmpty()){
            pNew.setNome(nome);
        }
        else{
            pNew.setNome(pOld.getNome());
        }

        boolean flag;

        Categoria categoria = new Categoria();
        do {
            flag = false;
            printCategorie(car.getCategorieWithDB());
            String cat = Utility.insertString("Inserisci l'id categoria da " + pOld.getCategoria().getNome() + " a: ");
            if(!cat.isEmpty()) {
                try {
                    categoria.setId(Integer.parseInt(cat));
                    pNew.setCategoria(categoria);
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
                categoria.setId(pOld.getCategoria().getId());
                pNew.setCategoria(categoria);
            }
        }while(flag);

        Materia materia = new Materia();
        do {
            flag = false;
            printMaterie(mr.getMaterieWithDB());
            String mat = Utility.insertString("Inserisci l'id materia da " + pOld.getMateria().getId() + " a: ");
            if(!mat.isEmpty()) {
                try {
                    materia.setId(Integer.parseInt(mat));
                    pNew.setMateria(materia);
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
                materia.setId(pOld.getMateria().getId());
                pNew.setMateria(materia);
            }
        }while(flag);

        Disponibilita disponibilita = new Disponibilita();
        do {
            flag = false;
            printDisponibilita(dr.getDisponibilitaWithDB());
            String disp = Utility.insertString("Inserisci l'id disponibilita da " + pOld.getDisponibilita().getId() + " a: ");
            if(!disp.isEmpty()) {
                try {
                    disponibilita.setId(Integer.parseInt(disp));
                    pNew.setDisponibilita(disponibilita);
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
                disponibilita.setId(pOld.getDisponibilita().getId());
                pNew.setDisponibilita(disponibilita);
            }
        }while(flag);

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
        oNew.setPrezzo_unitario(oOld.getPrezzo_unitario());
        oNew.setData_ordine(oOld.getData_ordine());
        oNew.setId(oOld.getId());

        boolean flag;

        do {
            flag = false;
            String quantita_ord = Utility.insertString("Inserisci la quantità ordinata da " + oOld.getQuantita() + " a: ");
            if(!quantita_ord.isEmpty()) {
                try {
                    oNew.setQuantita(Integer.parseInt(quantita_ord));
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
                oNew.setQuantita(oOld.getQuantita());
            }
        }while(flag);

        Stato stato = new Stato();
        do {
            flag = false;
            printStatus(sr.getStatusWithDB());
            String st = Utility.insertString("Inserisci l'id stato da " + oOld.getStato().getCode() + " a: ");
            if(!st.isEmpty()) {
                try {
                    stato.setId(Integer.parseInt(st));
                    oNew.setStato(stato);
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
                stato.setId(oOld.getStato().getId());
                oNew.setStato(stato);
            }
        }while(flag);

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
            System.out.println("***CATEGORIE DISPONIBILI***\n");

            for(Categoria categoria : categorie.values()){
                System.out.println("ID: " + categoria.getId() + ", \nNome: " + categoria.getNome());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUNA CATEGORIA PRESENTE***\n");
        }

    }

    public void printMaterie(HashMap<Integer, Materia> materie){
        if(!materie.isEmpty()){
            System.out.println("***MATERIE DISPONIBILI***\n");

            for(Materia materia : materie.values()){
                System.out.println("ID: " + materia.getId() + ", \nNome: " + materia.getNome());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUNA MATERIA PRESENTE***\n");
        }

    }

    public void printDisponibilita(HashMap<Integer, Disponibilita> disponibilitas){
        if(!disponibilitas.isEmpty()){
            System.out.println("***DISPONIBILITA PRESENTI***\n");

            for(Disponibilita disponibilita : disponibilitas.values()){
                System.out.println("ID: " + disponibilita.getId() + ", \nCodice: " + disponibilita.getCode());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUNA DISPONIBILITA PRESENTE***\n");
        }

    }

    public void printStatus(HashMap<Integer, Stato> stati){
        if(!stati.isEmpty()){
            System.out.println("***STATI PRESENTI***\n");

            for(Stato stato : stati.values()){
                System.out.println("ID: " + stato.getId() + ", \nCodice: " + stato.getCode());
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUN STATO PRESENTE***\n");
        }

    }

    public void printProdotti(HashMap<Integer, Prodotto> prodotti){
        if(!prodotti.isEmpty()){
            System.out.println("***PRODOTTI PRESENTI NEL GEOSTORE***\n");

            for(Prodotto prodotto : prodotti.values()){
                System.out.println("ID: " + prodotto.getId() + ", \nNome: " + prodotto.getNome() + ", \nPrezzo: " + prodotto.getPrezzo()+ ", \nDisponibilità: " + prodotto.getDisponibilita().getCode() + ", \nCategoria: " + prodotto.getCategoria().getNome() + ", \nMateria: " + prodotto.getMateria().getNome() + ", \nQuantità disponibile: " + prodotto.getQuantita_disp());
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
                System.out.println("ID: " + ordine.getId() + ", \nNome utente: " + ordine.getUtente().getNome() + ", \nCognome utente: " + ordine.getUtente().getCognome() + ", \nNome prodotto: " + ordine.getProdotto().getNome() + ", \nData ordine: " + ordine.getData_ordine() + ", \nQuantità ordinata: " + ordine.getQuantita() + ", \nPrezzo unitario: " + ordine.getPrezzo_unitario()+ ", \nStato ordine: " + ordine.getStato().getCode());
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

            System.out.println("Nome utente: " + ordine.getUtente().getNome() + "\nCognome utente: " + ordine.getUtente().getCognome() + ", \nData ordine totale: " + ordine.getData_ordine().toString().substring(0, 10) + ", \nPrezzo totale speso: " + ordine.getPrezzo_unitario());
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

    public void printUtente(Utente utente){
        if(utente != null && utente.getNome() != null){
            System.out.println("***PROFILO UTENTE GEOSTORE***\n");

            if(utente instanceof Amministratore){
                Amministratore admin = (Amministratore) utente;
                System.out.println("ID: " + admin.getId() + ", \nNome: " + admin.getNome() + ", \nCognome: " + admin.getCognome()+ ", \nEmail: " + admin.getEmail()+ ", \nPassword: " + admin.getPassword()+ ", \nTelefono: " + admin.getTelefono()+ ", \nIndirizzo: " + admin.getIndirizzo()+ ", \nCodice admin: " + admin.getCodeAdmin()+ ", \nPortafoglio: " + admin.getPortafoglio());
            }
            else{
                Cliente cliente = (Cliente) utente;
                System.out.println("ID: " + cliente.getId() + ", \nNome: " + cliente.getNome() + ", \nCognome: " + cliente.getCognome()+ ", \nEmail: " + cliente.getEmail()+ ", \nPassword: " + cliente.getPassword()+ ", \nTelefono: " + cliente.getTelefono()+ ", \nIndirizzo: " + cliente.getIndirizzo()+ ", \nPortafoglio: " + cliente.getPortafoglio());
            }
        }
        else{
            System.out.println("***NESSUN PROFILO UTENTE GEOSTORE***\n");
        }
    }

    public void printUtenti(HashMap<Integer, Utente> utenti){
        if(!utenti.isEmpty()){
            System.out.println("***UTENTI SU GEOSTORE***\n");

            for(Utente utente : utenti.values()){
                if(utente instanceof Amministratore){
                    Amministratore admin = (Amministratore) utente;
                    System.out.println("ID: " + admin.getId() + ", \nNome: " + admin.getNome() + ", \nCognome: " + admin.getCognome()+ ", \nEmail: " + admin.getEmail()+ ", \nPassword: " + admin.getPassword()+ ", \nTelefono: " + admin.getTelefono()+ ", \nIndirizzo: " + admin.getIndirizzo()+ ", \nCodice admin: " + admin.getCodeAdmin()+ ", \nPortafoglio: " + admin.getPortafoglio());
                }
                else{
                    Cliente cliente = (Cliente) utente;
                    System.out.println("ID: " + cliente.getId() + ", \nNome: " + cliente.getNome() + ", \nCognome: " + cliente.getCognome()+ ", \nEmail: " + cliente.getEmail()+ ", \nPassword: " + cliente.getPassword()+ ", \nTelefono: " + cliente.getTelefono()+ ", \nIndirizzo: " + cliente.getIndirizzo()+ ", \nPortafoglio: " + cliente.getPortafoglio());
                }
                System.out.println("-----------------------------------------------------------");
            }
        }
        else{
            System.out.println("***NESSUN UTENTE PRESENTE SU GEOSTORE***\n");
        }
    }
}
