package src.main.java.view;

import src.main.java.model.*;
import src.main.java.utility.Utility;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class View {

    public int readMenuCliente(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Visualizza oggetti");
        System.out.println("2) Ordina oggetto");
        System.out.println("3) Visualizza ordini");
        System.out.println("4) Elimina ordine");
        System.out.println("5) Visualizza oggetti per categoria");
        System.out.println("6) Visualizza oggetti per materia");
        System.out.println("7) Visualizza oggetti disponibili");
        System.out.println("8) Visualizza ordini totali giornalieri");

        System.out.println("0) ESCI");

        return Utility.insertInt("******");
    }

    public int readMenuAdmin(){
        System.out.println("***MENU GEOSTORE***\n");
        System.out.println("1) Visualizza oggetti");
        System.out.println("2) Ordina oggetto");
        System.out.println("3) Visualizza ordini");
        System.out.println("4) Elimina ordine");
        System.out.println("5) Visualizza oggetti per categoria");
        System.out.println("6) Visualizza oggetti per materia");
        System.out.println("7) Visualizza oggetti disponibili");
        System.out.println("8) Crea/Modifica/Elimina categoria");
        System.out.println("9) Crea/Modifica/Elimina materia");
        System.out.println("10) Visualizza ordini totali giornalieri");

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
        //o.setData_ordine(OffsetDateTime.now());
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

    public void printCategorie(HashMap<Integer, Categoria> categorie){
        System.out.println("***CATEGORIE***");

        for(Categoria categoria : categorie.values()){
            System.out.println("ID: " + categoria.getId() + ", Nome: " + categoria.getNome());
        }
    }

    public void printOggetti(HashMap<Integer, Oggetto> oggetti){
        System.out.println("***PRODOTTI PRESENTI NEL GEOSTORE***\n\n");

        for(Oggetto oggetto : oggetti.values()){
            System.out.println("ID: " + oggetto.getId() + ", Nome: " + oggetto.getNome() + ", Prezzo: " + oggetto.getPrezzo()+ ", Disponibilità: " + oggetto.getDisponibilita().getCode() + ", Categoria: " + oggetto.getCategoria().getNome() + ", Materia: " + oggetto.getMateria().getNome() + ", Quantità disponibile: " + oggetto.getQuantita_disp());
        }
    }

    public void printCategoria(Categoria categoria){
        System.out.println("ID: " + categoria.getId() + ", Nome: " + categoria.getNome());
    }
}
