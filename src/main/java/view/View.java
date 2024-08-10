package src.main.java.view;

import src.main.java.utility.Utility;

public class View {


    public int readMenu(){
        System.out.println("***MENU GEOSTORE***");
        System.out.println("1) Inserisci categoria");
        System.out.println("2) Elimina categoria");
        System.out.println("3) Visualizza categorie");
        System.out.println("0) ESCI");

        return Utility.insertInt("***SCEGLI L'OPZIONE***");
    }


}
