package src.main.java.controller;

import src.main.java.model.*;
import src.main.java.utility.Utility;
import src.main.java.view.PreviewView;
import src.main.java.view.View;

public class AppTerminal {

     public static void main(String[] args){

          int valueInput = 0, chooseValue = 0;
          boolean flag;
          View view = new View();
          OggettoRepository or = new OggettoRepository();
          ClienteRepository cr = new ClienteRepository();
          Cliente c;

          valueInput = view.readAdminOrUserMenu();
          if(valueInput == 1){
               Utility.msgInf("GEOSTORE", "Sei un cliente Geostore\n");
               c = new Cliente();
               view.maskCheckUser(c);
               c = cr.checkUser(c.getEmail());

               if(c != null && c.getEmail() != null){
                    Utility.msgInf("GEOSTORE", "Accesso approvato\n");
                    do{
                         valueInput = view.readMenuCliente();
                         switch(valueInput){
                              case 0:
                                   Utility.msgInf("GEOSTORE", "Nessun operazione");
                                   break;
                              case 1:
                                   Utility.msgInf("GEOSTORE", "Elenco oggetti\n\n");
                                   view.printOggetti(or.getOggettiWithDB());
                                   break;
                              default:
                                   Utility.msgErr("GEOSTORE", "Non so cosa hai inserito");
                                   break;
                         }

                         if(Utility.insertString("VUOI ANDARE AVANTI?").equalsIgnoreCase("s")){
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
                    Utility.msgErr("GEOSTORE", "Accesso negato - email errata\n");
               }

          }
          else if(chooseValue == 2){
               Utility.msgInf("GEOSTORE", "Sei un amministratore Geostore\n");
               c = new Amministratore();
               view.maskCheckUser(c);
               do{
                    valueInput = view.readMenuAdmin();
                    switch(valueInput){
                         case 0:
                              Utility.msgInf("GEOSTORE", "Nessun operazione");
                              break;
                         case 1:
                              Utility.msgInf("GEOSTORE", "Elenco oggetti\n\n");
                              view.printOggetti(or.getOggettiWithDB());
                              break;
                         default:
                              Utility.msgErr("GEOSTORE", "Non so cosa hai inserito");
                              break;
                    }

                    if(Utility.insertString("VUOI ANDARE AVANTI?\n").equalsIgnoreCase("s")){
                         flag = true;
                         System.out.println("***VA BENE***\n");
                    }
                    else{
                         flag = false;
                         System.out.println("***OK COME VUOI***\n");
                    }
               }while(flag);

          }
          else{
               Utility.msgErr("GEOSTORE", "Non so cosa hai inserito\n\n");
          }

          Utility.msgInf("GEOSTORE","***GRAZIE PER AVER PROVATO IL MENU***");

     }
}
