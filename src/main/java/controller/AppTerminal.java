package src.main.java.controller;

import src.main.java.model.Categoria;
import src.main.java.model.CategoriaRepository;
import src.main.java.utility.Utility;
import src.main.java.view.View;

public class AppTerminal {

     public static void main(String[] args){

          int num = 0, valueInput = 0;
          boolean flag;
          String preValueInput = "";
          View view = new View();
          CategoriaRepository cr = new CategoriaRepository();
          Categoria c;

          preValueInput = view.readPreMenu();
          if(preValueInput.equalsIgnoreCase("s")){
               do{
                    valueInput = view.readMenu();
                    switch(valueInput){
                         case 0:
                              Utility.msgInf("GEOSTORE", "nulla");
                              break;
                         case 1:
                              Utility.msgInf("GEOSTORE", "Visualizzazione delle categorie");
                              view.printCategorie(cr.getCategorie());
                              break;
                         case 2:
                              Utility.msgInf("GEOSTORE", "Inserimento categoria");
                              c = new Categoria();
                              view.maskInsertCategoria(c);
                              cr.insertCategoria(c.getId(), c);
                              break;
                         case 3:
                              Utility.msgInf("GEOSTORE", "Aggiornamento categoria");
                              num = 1;
                              Utility.msgInf("PROVA", "per cui il numero è: " + num);
                              break;
                         default:
                              Utility.msgErr("PROVA", "non so cosa hai inserito");
                              break;
                    }

                    if(Utility.insertString("VUOI RIPROVARE?").equalsIgnoreCase("s")){
                         flag = true;
                         System.out.println("***VA BENE***");
                    }
                    else{
                         flag = false;
                         System.out.println("***OK COME VUOI***");
                    }
               }while(flag);
          }
          else{
               System.out.println("***OK COME VUOI***");
          }

          System.out.println("***GRAZIE PER AVER PROVATO IL MENU GEOSTORE***");

     }
}
