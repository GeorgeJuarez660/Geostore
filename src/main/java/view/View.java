package src.main.java.view;

import src.main.java.model.Categoria;
import src.main.java.model.Cliente;
import src.main.java.utility.Utility;

import java.util.HashMap;

public class View {


    public String readPreMenu(){
        System.out.println("***BENVENUTO***");

        return Utility.insertString("Vuoi andare avanti?");
    }

    public

    public int readMenu(){
        System.out.println("***MENU GEOSTORE***");
        System.out.println("1) Visualizza categorie");
        System.out.println("2) Inserisci categoria");
        System.out.println("3) Modifica categoria");
        System.out.println("4) Elimina categoria");
        System.out.println("0) ESCI");

        return Utility.insertInt("***SCEGLI L'OPZIONE***");
    }

    public int readChooseMenu(){
        System.out.println("***SCEGLI MENU***");
        System.out.println("1) Menu locale");
        System.out.println("2) Menu con database");

        return Utility.insertInt("***SCEGLI L'OPZIONE***");
    }

    public int readAdminOrUserMenu(){
        System.out.println("***BENVENUTO***\n\n");
        System.out.println("***SEI UN CLIENTE O ADMIN?***");
        System.out.println("1) Cliente");
        System.out.println("2) Admin");

        return Utility.insertInt("***A TUA SCELTA***");
    }

    public void maskCheckCliente(Cliente c){
        c.setEmail(Utility.insertString("Inserisci l'email cliente"));
    }

    public void maskCheckAdmin(Cliente c){
        c.setEmail(Utility.insertString("Inserisci l'email cliente"));
        c.setCodeAdmin(Utility.insertString("Inserisci il codice amministratore"));
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

    public void printCategoria(Categoria categoria){
        System.out.println("ID: " + categoria.getId() + ", Nome: " + categoria.getNome());
    }
}
