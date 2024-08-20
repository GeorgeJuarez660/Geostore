package src.main.java.controller;

import src.main.java.model.Categoria;
import src.main.java.model.CategoriaRepository;
import src.main.java.utility.Utility;
import src.main.java.view.View;

public class PreviewConditionTwo {

     public static void main(String[] args){

          int num = 0, valueInput = 0, chooseValue = 0;
          boolean flag;
          String preValueInput = "";
          View view = new View();
          CategoriaRepository cr = new CategoriaRepository();
          Categoria c;

          preValueInput = view.readPreMenu();
          if(preValueInput.equalsIgnoreCase("s")){
               chooseValue = view.readChooseMenu();
               if(chooseValue == 1){
                    Utility.msgInf("GEOSTORE", "Hai scelto il menu locale\n\n");
                    do{
                         valueInput = view.readMenu();
                         switch(valueInput){
                              case 0:
                                   Utility.msgInf("GEOSTORE", "Nessun operazione");
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
                                   c = cr.getCategoria(Utility.insertString("Inserisci il nome categoria: "));

                                   if(c != null){
                                        Utility.msgInf("GEOSTORE", "Categoria trovata");
                                        view.printCategoria(c);
                                        Categoria cNew = view.maskUpdateCategoria(c, new Categoria());

                                        cr.updateCategoria(cNew.getId(), cNew);
                                   }
                                   else{
                                        Utility.msgErr("GEOSTORE", "Categoria non trovata");
                                   }

                                   break;
                              case 4:
                                   Utility.msgInf("GEOSTORE", "Eliminazione categoria");
                                   c = cr.getCategoria(Utility.insertString("Inserisci il nome categoria: "));

                                   if(c != null){
                                        Utility.msgInf("GEOSTORE", "Categoria trovata");
                                        view.printCategoria(c);
                                        if(Utility.insertString("Sei sicuro di voler eliminare questa categoria?").equalsIgnoreCase("s")){
                                             cr.deleteCategoria(c.getId());
                                             Utility.msgInf("GEOSTORE", "Categoria eliminata");
                                        }
                                        else{
                                             Utility.msgInf("GEOSTORE", "Operazione annullata");
                                        }
                                   }
                                   else{
                                        Utility.msgErr("GEOSTORE", "Categoria non trovata");
                                   }

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
               else if(chooseValue == 2){
                    System.out.println(System.getProperty("java.class.path"));
                    Utility.msgInf("GEOSTORE", "Hai scelto il menu con database\n\n");
                    do{
                         valueInput = view.readMenu();
                         switch(valueInput){
                              case 0:
                                   Utility.msgInf("GEOSTORE", "Nessun operazione");
                                   break;
                              case 1:
                                   Utility.msgInf("GEOSTORE", "Visualizzazione delle categorie");
                                   view.printCategorie(cr.getCategorieWithDB());
                                   break;
                              case 2:
                                   Utility.msgInf("GEOSTORE", "Inserimento categoria");
                                   c = new Categoria();
                                   view.maskInsertCategoria(c);
                                   cr.insertCategoriaWithDB(c.getId(), c);
                                   break;
                              case 3:
                                   Utility.msgInf("GEOSTORE", "Aggiornamento categoria");
                                   c = cr.getCategoriaWithDB(Utility.insertString("Inserisci il nome categoria: "));

                                   if(c != null){
                                        Utility.msgInf("GEOSTORE", "Categoria trovata");
                                        view.printCategoria(c);
                                        Categoria cNew = view.maskUpdateCategoria(c, new Categoria());

                                        cr.updateCategoriaWithDB(cNew.getId(), cNew);
                                        Utility.msgErr("GEOSTORE", "Categoria modificata");
                                   }
                                   else{
                                        Utility.msgErr("GEOSTORE", "Categoria non trovata");
                                   }

                                   break;
                              case 4:
                                   Utility.msgInf("GEOSTORE", "Eliminazione categoria");
                                   c = cr.getCategoriaWithDB(Utility.insertString("Inserisci il nome categoria: "));

                                   if(c != null){
                                        Utility.msgInf("GEOSTORE", "Categoria trovata");
                                        view.printCategoria(c);
                                        if(Utility.insertString("Sei sicuro di voler eliminare questa categoria?").equalsIgnoreCase("s")){
                                             cr.deleteCategoriaWithDB(c.getId());
                                             Utility.msgInf("GEOSTORE", "Categoria eliminata");
                                        }
                                        else{
                                             Utility.msgInf("GEOSTORE", "Operazione annullata");
                                        }
                                   }
                                   else{
                                        Utility.msgErr("GEOSTORE", "Categoria non trovata");
                                   }

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
                    Utility.msgErr("GEOSTORE", "Non so cosa hai inserito\n\n");
               }
          }
          else{
               Utility.msgInf("GEOSTORE","***OK COME VUOI***");
          }

          Utility.msgInf("GEOSTORE","***GRAZIE PER AVER PROVATO IL MENU***");

     }
}
