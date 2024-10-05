package src.main.java.controller;

import src.main.java.model.*;
import src.main.java.utility.Utility;
import src.main.java.view.View;

public class AppJavaSwing {

     public static void main(String[] args){

          int valueInput = 0;
          boolean flagMenu, flagReplay, flagLogin;
          View view = new View();
          UtenteRepository ur = new UtenteRepository();
          Service service = new Service();

          do {
               valueInput = view.registerOrLogin();
               if(valueInput == 1){
                    Utility.msgInf("GEOSTORE", "Registrazione utente\n\n");
                    service.creazioneUtente();
                    flagReplay = true;
               }
               else if(valueInput == 2){
                    Utility.msgInf("GEOSTORE", "Accesso utente\n\n");

                    do{
                         valueInput = view.readAdminOrUserMenu();

                         if(valueInput == 1){
                              Utility.msgInf("GEOSTORE", "Accedi come cliente Geostore\n");
                              Cliente c = new Cliente();
                              view.maskCheckUser(c);
                              c = ur.checkCliente(c.getEmail(), c.getPassword());

                              if(c != null && c.getEmail() != null){
                                   Utility.msgInf("GEOSTORE", "Accesso approvato\n");
                                   do{
                                        valueInput = view.readMenuCliente();
                                        switch(valueInput){
                                             case 0:
                                                  Utility.msgInf("GEOSTORE", "Nessuna operazione\n\n");
                                                  break;
                                             case 1:
                                                  Utility.msgInf("GEOSTORE", "Profilo utente\n\n");
                                                  service.profiloUtente(c);
                                                  break;
                                             case 2:
                                                  Utility.msgInf("GEOSTORE", "Modifica profilo utente\n\n");
                                                  service.modificaUtente(c, false);
                                                  break;
                                             case 3:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti\n\n");
                                                  service.elencoProdotti();
                                                  break;
                                             case 4:
                                                  Utility.msgInf("GEOSTORE", "Ordinazione di un prodotto\n\n");
                                                  boolean flagOrder;
                                                  do{
                                                       service.ordinazioneProdotto(c);
                                                       boolean flagQuestion;
                                                       do{
                                                            String cQuestion = Utility.insertString("\nVUOI RIPETERE? (s/n)");
                                                            if(cQuestion.equalsIgnoreCase("s")){
                                                                 flagOrder = true;
                                                                 flagQuestion = false;
                                                                 System.out.println("\n***VA BENE***");
                                                            }
                                                            else if(cQuestion.equalsIgnoreCase("n")){
                                                                 flagOrder = false;
                                                                 flagQuestion = false;
                                                                 System.out.println("\n***OK COME VUOI***");
                                                            }
                                                            else{
                                                                 flagOrder = false;
                                                                 flagQuestion = true;
                                                                 System.out.println("\n***RILEGGI LA DOMANDA***");
                                                            }
                                                       }while(flagQuestion);
                                                  }while(flagOrder);
                                                  break;
                                             case 5:
                                                  Utility.msgInf("GEOSTORE", "Elenco ordini effettuati\n\n");
                                                  service.ordiniEffettuati(c, false);
                                                  break;
                                             case 6:
                                                  Utility.msgInf("GEOSTORE", "Eliminazione ordine\n\n");
                                                  service.eliminazioneOrdine(c, true);
                                                  break;
                                             case 7:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti per categoria\n\n");
                                                  service.prodottiViaCategoria();
                                                  break;
                                             case 8:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti per materia\n\n");
                                                  service.prodottiViaMateria();
                                                  break;
                                             case 9:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti disponibili\n\n");
                                                  service.prodottiDisponibili();
                                                  break;
                                             case 10:
                                                  Utility.msgInf("GEOSTORE", "Ordini totali giornalieri\n\n");
                                                  service.ordiniTotaliGiornalieri(c);
                                                  break;
                                             default:
                                                  Utility.msgInf("GEOSTORE", "Non so cosa hai inserito");
                                                  break;
                                        }

                                        boolean flagQuestion;
                                        do{
                                             String cQuestion = Utility.insertString("\nVUOI ANDARE AVANTI CON IL MENU? (s/n)");
                                             if(cQuestion.equalsIgnoreCase("s")){
                                                  flagMenu = true;
                                                  flagQuestion = false;
                                                  System.out.println("\n***VA BENE***");
                                             }
                                             else if(cQuestion.equalsIgnoreCase("n")){
                                                  flagMenu = false;
                                                  flagQuestion = false;
                                                  System.out.println("\n***OK COME VUOI***");
                                             }
                                             else{
                                                  flagMenu = false;
                                                  flagQuestion = true;
                                                  System.out.println("\n***RILEGGI LA DOMANDA***");
                                             }
                                        }while(flagQuestion);


                                   }while(flagMenu);
                              }
                              else{
                                   Utility.msgInf("GEOSTORE", "Accesso negato - email e/o password errati\n");
                              }

                         }
                         else if(valueInput == 2){
                              Utility.msgInf("GEOSTORE", "Accedi come amministratore Geostore\n");
                              Amministratore a = new Amministratore();
                              view.maskCheckUser(a);
                              a = ur.checkAdmin(a.getEmail(), a.getPassword(), a.getCodeAdmin());
                              if(a != null && a.getEmail() != null){
                                   Utility.msgInf("GEOSTORE", "Accesso approvato\n");

                                   do{
                                        valueInput = view.readMenuAdmin();
                                        switch(valueInput){
                                             case 0:
                                                  Utility.msgInf("GEOSTORE", "Nessuna operazione\n\n");
                                                  break;
                                             case 1:
                                                  Utility.msgInf("GEOSTORE", "Profilo utente\n\n");
                                                  service.profiloUtente(a);
                                                  break;
                                             case 2:
                                                  Utility.msgInf("GEOSTORE", "Utenti iscritti su Geostore\n\n");
                                                  service.elencoUtenti();
                                                  break;
                                             case 3:
                                                  Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina cliente\n\n");
                                                  int chooseC = Utility.insertInt("1 - Crea, 2 - Modifica, 3 - Elimina");

                                                  if(chooseC == 1){
                                                       service.creazioneUtente();
                                                  }
                                                  else if(chooseC == 2){
                                                      service.modificaUtente(a, true);
                                                  }
                                                  else if(chooseC == 3){
                                                       service.eliminazioneUtente();
                                                  }
                                                  else{
                                                       Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
                                                  }
                                                  break;
                                             case 4:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti\n\n");
                                                  service.elencoProdotti();
                                                  break;
                                             case 5:
                                                  Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina prodotto\n\n");
                                                  int chooseP = Utility.insertInt("1 - Crea, 2 - Modifica, 3 - Elimina");

                                                  if(chooseP == 1){
                                                       service.creazioneProdotto();
                                                  }
                                                  else if(chooseP == 2){
                                                       service.modificaProdotto();
                                                  }
                                                  else if(chooseP == 3){
                                                       service.eliminazioneProdotto();
                                                  }
                                                  else{
                                                       Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
                                                  }
                                                  break;
                                             case 6:
                                                  Utility.msgInf("GEOSTORE", "Ordinazione di un prodotto\n\n");
                                                  boolean flagOrder;
                                                  do{
                                                       service.ordinazioneProdotto(a);
                                                       boolean flagQuestion;
                                                       do{
                                                            String cQuestion = Utility.insertString("\nVUOI RIPETERE? (s/n)");
                                                            if(cQuestion.equalsIgnoreCase("s")){
                                                                 flagOrder = true;
                                                                 flagQuestion = false;
                                                                 System.out.println("\n***VA BENE***");
                                                            }
                                                            else if(cQuestion.equalsIgnoreCase("n")){
                                                                 flagOrder = false;
                                                                 flagQuestion = false;
                                                                 System.out.println("\n***OK COME VUOI***");
                                                            }
                                                            else{
                                                                 flagOrder = false;
                                                                 flagQuestion = true;
                                                                 System.out.println("\n***RILEGGI LA DOMANDA***");
                                                            }
                                                       }while(flagQuestion);
                                                  }while(flagOrder);
                                                  break;
                                             case 7:
                                                  Utility.msgInf("GEOSTORE", "Elenco ordini effettuati\n\n");
                                                  service.ordiniEffettuati(a, true);
                                                  break;
                                             case 8:
                                                  Utility.msgInf("GEOSTORE", "Modifica/elimina ordine\n\n");
                                                  int choose2 = Utility.insertInt("1 - Modifica, 2 - Elimina");
                                                  if(choose2 == 1){
                                                       service.modificaOrdine();
                                                  }
                                                  else if(choose2 == 2){
                                                       service.eliminazioneOrdine(a, false);
                                                  }
                                                  else{
                                                       Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
                                                  }
                                                  break;
                                             case 9:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti per categoria\n\n");
                                                  service.prodottiViaCategoria();
                                                  break;
                                             case 10:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti per materia\n\n");
                                                  service.prodottiViaMateria();
                                                  break;
                                             case 11:
                                                  Utility.msgInf("GEOSTORE", "Elenco prodotti disponibili\n\n");
                                                  service.prodottiDisponibili();
                                                  break;
                                             case 12:
                                                  Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina categoria\n\n");
                                                  int choose3 = Utility.insertInt("1 - Crea, 2 - Modifica, 3 - Elimina");

                                                  if(choose3 == 1){
                                                       service.creazioneCategoria();
                                                  }
                                                  else if(choose3 == 2){
                                                       service.modificaCategoria();
                                                  }
                                                  else if(choose3 == 3){
                                                       service.eliminazioneCategoria();
                                                  }
                                                  else{
                                                       Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
                                                  }

                                                  break;
                                             case 13:
                                                  Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina materia\n\n");
                                                  int choose4 = Utility.insertInt("1 - Crea, 2 - Modifica, 3 - Elimina");

                                                  if(choose4 == 1){
                                                       service.creazioneMateria();
                                                  }
                                                  else if(choose4 == 2){
                                                       service.modificaMateria();
                                                  }
                                                  else if(choose4 == 3){
                                                       service.eliminazioneMateria();
                                                  }
                                                  else{
                                                       Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
                                                  }
                                                  break;
                                             case 14:
                                                  Utility.msgInf("GEOSTORE", "Ordini totali giornalieri\n\n");
                                                  service.ordiniTotaliGiornalieri(a);
                                                  break;
                                             default:
                                                  Utility.msgInf("GEOSTORE", "Non so cosa hai inserito");
                                                  break;
                                        }

                                        boolean flagQuestion;
                                        do{
                                             String cQuestion = Utility.insertString("\nVUOI ANDARE AVANTI CON IL MENU? (s/n)");
                                             if(cQuestion.equalsIgnoreCase("s")){
                                                  flagMenu = true;
                                                  flagQuestion = false;
                                                  System.out.println("\n***VA BENE***");
                                             }
                                             else if(cQuestion.equalsIgnoreCase("n")){
                                                  flagMenu = false;
                                                  flagQuestion = false;
                                                  System.out.println("\n***OK COME VUOI***");
                                             }
                                             else{
                                                  flagMenu = false;
                                                  flagQuestion = true;
                                                  System.out.println("\n***RILEGGI LA DOMANDA***");
                                             }
                                        }while(flagQuestion);

                                   }while(flagMenu);
                              }
                              else{
                                   Utility.msgInf("GEOSTORE", "Accesso negato - email, password e/o codice admin errati\n");
                              }

                         }
                         else{
                              Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n\n");
                         }

                         boolean flagTry;

                         do{
                              String question = Utility.insertString("\nVUOI PROCEDERE CON IL LOGOUT? (s/n)");
                              if(question.equalsIgnoreCase("s")){
                                   flagLogin = true;
                                   flagTry = false;
                                   System.out.println("\n***VA BENE***");
                              }
                              else if(question.equalsIgnoreCase("n")){
                                   flagLogin = false;
                                   flagTry = false;
                                   System.out.println("\n***OK COME VUOI***");
                              }
                              else{
                                   flagTry = true;
                                   flagLogin = false;
                                   System.out.println("\n***RILEGGI LA DOMANDA***");
                              }
                         }while(flagTry);

                    }while(flagLogin);

                    boolean flagTryTwo;

                    do{
                         String question = Utility.insertString("\nVUOI TORNARE INDIETRO? (s/n)");
                         if(question.equalsIgnoreCase("s")){
                              flagReplay = true;
                              flagTryTwo = false;
                              System.out.println("\n***VA BENE***");
                         }
                         else if(question.equalsIgnoreCase("n")){
                              flagReplay = false;
                              flagTryTwo = false;
                              System.out.println("\n***OK COME VUOI***");
                         }
                         else{
                              flagTryTwo = true;
                              flagReplay = false;
                              System.out.println("\n***RILEGGI LA DOMANDA***");
                         }
                    }while(flagTryTwo);

               }
               else if(valueInput == 0){
                    flagReplay = false;
                    Utility.msgInf("GEOSTORE", "***VA BENE***\n");

               }
               else{
                    flagReplay = true;
                    Utility.msgInf("GEOSTORE", "Rileggi\n");
               }

          }while(flagReplay);

          Utility.msgInf("GEOSTORE","***ARRIVEDERCI E GRAZIE***");

     }
}
