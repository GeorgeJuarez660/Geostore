package src.main.java.controller;

import src.main.java.model.*;
import src.main.java.utility.Utility;
import src.main.java.view.PreviewView;
import src.main.java.view.View;

import java.sql.Date;

public class AppTerminal {

     public static void main(String[] args){

          int valueInput = 0;
          boolean flag;
          View view = new View();
          OggettoRepository or = new OggettoRepository();
          ClienteRepository cr = new ClienteRepository();
          OrdineRepository odr = new OrdineRepository();
          Cliente c;
          Categoria cat;
          Materia m;
          Ordine o;
          Oggetto og;

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
                                   Utility.msgInf("GEOSTORE", "Nessun operazione\n\n");
                                   break;
                              case 1:
                                   Utility.msgInf("GEOSTORE", "Profilo utente\n\n");
                                   view.printUtente(cr.getClienteWithDB(c.getNome()));
                                   break;
                              case 2:
                                   Utility.msgInf("GEOSTORE", "Elenco oggetti\n\n");
                                   view.printOggetti(or.getOggettiWithDB());
                                   break;
                              case 3:
                                   Utility.msgInf("GEOSTORE", "Ordinazione di un prodotto\n\n");
                                   view.printOggetti(or.getOggettiDispWithDB());
                                   o = new Ordine();
                                   view.maskInsertOrdine(o, c);
                                   og = or.getOggettoDispWithDB(o.getOggetto().getNome());

                                   if(og != null && og.getNome() != null){
                                        Utility.msgInf("GEOSTORE", "Il prodotto è disponibile\n");
                                        o.setOggetto(og);

                                        if(o.getQuantita() <= og.getQuantita_disp()){
                                             o.setPrezzo_unitario(og.getPrezzo());

                                             int num = odr.insertOrdineWithDB(null, o);
                                             if(num > 0){
                                                  Utility.msgInf("GEOSTORE", "Ordine effettuato");
                                             }
                                             else{
                                                  Utility.msgInf("GEOSTORE", "Ordine non effettuato");
                                             }
                                        }
                                        else{
                                             Utility.msgErr("GEOSTORE", "La quantità ordinata supera quella disponibile");
                                        }
                                   }
                                   else{
                                        Utility.msgErr("GEOSTORE", "L'oggetto ordinato non è disponibile oppure è inesistente");
                                   }
                                   break;
                              case 4:
                                   Utility.msgInf("GEOSTORE", "Elenco ordini effettuati\n\n");
                                   view.printOrdini(odr.getOrdiniByUserWithDB(c.getNome()));
                                   break;
                              case 5:
                                   Utility.msgInf("GEOSTORE", "Eliminazione ordine\n\n");
                                   view.printOrdini(odr.getOrdiniByUserWithDB(c.getNome()));

                                   o = odr.getOrdineByUserAndObjNameWithDB(c.getNome(), Utility.insertInt("Inserisci l'id ordine"));

                                   if(o != null && o.getOggetto() != null && o.getOggetto().getNome() != null){
                                        Utility.msgInf("GEOSTORE", "Ordine trovato\n");
                                        if(Utility.insertString("Sei sicuro di voler eliminare quest'ordine?").equalsIgnoreCase("s")){
                                             odr.deleteOrdineWithDB(o.getId());
                                             Utility.msgInf("GEOSTORE", "Ordine eliminato");
                                        }
                                        else{
                                             Utility.msgInf("GEOSTORE", "Operazione annullata");
                                        }
                                   }
                                   else{
                                        Utility.msgInf("GEOSTORE", "Ordine non trovato\n");
                                   }
                                   break;
                              case 6:
                                   Utility.msgInf("GEOSTORE", "Elenco oggetti per categoria\n\n");
                                   cat = new Categoria();
                                   view.maskObjViaCat(cat);
                                   view.printOggetti(or.getOggettiViaCategoriaWithDB(cat.getNome()));
                                   break;
                              case 7:
                                   Utility.msgInf("GEOSTORE", "Elenco oggetti per materia\n\n");
                                   m = new Materia();
                                   view.maskObjViaMat(m);
                                   view.printOggetti(or.getOggettiViaMateriaWithDB(m.getNome()));
                                   break;
                              case 8:
                                   Utility.msgInf("GEOSTORE", "Elenco oggetti dispobili\n\n");
                                   view.printOggetti(or.getOggettiDispWithDB());
                                   break;
                              case 9:
                                   Utility.msgInf("GEOSTORE", "Ordini totali giornalieri\n\n");
                                   String chooseDate = Utility.insertString("Inserisci la data in formato yyyy-mm-dd");
                                   Date date = Utility.convertDate(chooseDate);
                                   view.printOggetti(or.getOggettiDispWithDB());
                                   break;
                              default:
                                   Utility.msgErr("GEOSTORE", "Non so cosa hai inserito");
                                   break;
                         }

                         if(Utility.insertString("\nVUOI ANDARE AVANTI?").equalsIgnoreCase("s")){
                              flag = true;
                              System.out.println("\n***VA BENE***");
                         }
                         else{
                              flag = false;
                              System.out.println("\n***OK COME VUOI***");
                         }
                    }while(flag);
               }
               else{
                    Utility.msgErr("GEOSTORE", "Accesso negato - email errata\n");
               }

          }
          else if(valueInput == 2){
               Utility.msgInf("GEOSTORE", "Sei un amministratore Geostore\n");
               c = new Amministratore();
               view.maskCheckUser(c);
               do{
                    valueInput = view.readMenuAdmin();
                    switch(valueInput){
                         case 0:
                              Utility.msgInf("GEOSTORE", "Nessun operazione\n\n");
                              break;
                         case 1:
                              Utility.msgInf("GEOSTORE", "Profilo utente\n\n");
                              view.printUtente(cr.getClienteWithDB(c.getNome()));
                              break;
                         case 2:
                              Utility.msgInf("GEOSTORE", "Utenti iscritti su Geostore\n\n");
                              view.printUtenti(cr.getClientiWithDB());
                              break;
                         case 3:
                              Utility.msgInf("GEOSTORE", "Elenco oggetti\n\n");
                              view.printOggetti(or.getOggettiWithDB());
                              break;
                         case 4:
                              Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina oggetto\n\n");

                              break;
                         case 5:
                              Utility.msgInf("GEOSTORE", "Ordinazione di un prodotto\n\n");
                              view.printOggetti(or.getOggettiDispWithDB());
                              o = new Ordine();
                              view.maskInsertOrdine(o, c);
                              og = or.getOggettoDispWithDB(o.getOggetto().getNome());

                              if(og != null && og.getNome() != null){
                                   Utility.msgInf("GEOSTORE", "Il prodotto è disponibile\n");
                                   o.setOggetto(og);

                                   if(o.getQuantita() <= og.getQuantita_disp()){
                                        o.setPrezzo_unitario(og.getPrezzo());

                                        int num = odr.insertOrdineWithDB(null, o);
                                        if(num > 0){
                                             Utility.msgInf("GEOSTORE", "Ordine effettuato\n");
                                        }
                                        else{
                                             Utility.msgInf("GEOSTORE", "Ordine non effettuato\n");
                                        }
                                   }
                                   else{
                                        Utility.msgErr("GEOSTORE", "La quantità ordinata supera quella disponibile\n");
                                   }
                              }
                              else{
                                   Utility.msgErr("GEOSTORE", "L'oggetto ordinato non è disponibile oppure è inesistente\n");
                              }
                              break;
                         case 6:
                              Utility.msgInf("GEOSTORE", "Elenco ordini effettuati\n\n");
                              int choose = Utility.insertInt("1 - tuoi ordini, 2 - di un'altra persona ");
                              if(choose == 1){
                                   Utility.msgInf("GEOSTORE", "Hai scelto i tuoi ordini\n");
                                   view.printOrdini(odr.getOrdiniByUserWithDB(c.getNome()));
                              }
                              else if(choose == 2){
                                   Utility.msgInf("GEOSTORE", "Hai scelto di sapere quello di qualcun'altro\n");
                                   c.setNome(Utility.insertString("Inserisci il nome di un'altra persona"));
                                   view.printOrdini(odr.getOrdiniByUserWithDB(c.getNome()));
                              }
                              else{
                                   Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
                              }

                              break;
                         case 7:
                              Utility.msgInf("GEOSTORE", "Elenco ordini generali effettuati\n\n");
                              view.printOrdini(odr.getOrdiniWithDB());
                              break;
                         case 8:
                              Utility.msgInf("GEOSTORE", "Modifica/elimina ordine\n\n");
                              view.printOrdini(odr.getOrdiniWithDB());
                              int choose2 = Utility.insertInt("1 - Modifica, 2 - Elimina");
                              if(choose2 == 1){
                                   o = odr.getOrdineWithDB(Utility.insertInt("Inserisci l'id ordine"));

                                   if(o != null && o.getOggetto() != null && o.getOggetto().getNome() != null){
                                        Utility.msgInf("GEOSTORE", "Ordine trovato\n");

                                        Ordine oNew = view.maskUpdateOrdine(o, new Ordine());

                                        int num = odr.updateOrdineWithDB(oNew.getId(), oNew);

                                        if(num > 0){
                                             Utility.msgInf("GEOSTORE", "Ordine aggiornato\n");
                                        }
                                        else{
                                             Utility.msgInf("GEOSTORE", "Ordine non aggiornato\n");
                                        }

                                   }
                                   else{
                                        Utility.msgInf("GEOSTORE", "Ordine non trovato\n");
                                   }
                              }
                              else if(choose2 == 2){
                                   o = odr.getOrdineWithDB(Utility.insertInt("Inserisci l'id ordine"));

                                   if(o != null && o.getOggetto() != null && o.getOggetto().getNome() != null){
                                        Utility.msgInf("GEOSTORE", "Ordine trovato\n");
                                        if(Utility.insertString("Sei sicuro di voler eliminare quest'ordine?").equalsIgnoreCase("s")){
                                             odr.deleteOrdineWithDB(o.getId());
                                             Utility.msgInf("GEOSTORE", "Ordine eliminato\n");
                                        }
                                        else{
                                             Utility.msgInf("GEOSTORE", "Operazione annullata\n");
                                        }
                                   }
                                   else{
                                        Utility.msgInf("GEOSTORE", "Ordine non trovato\n");
                                   }
                              }
                              else{
                                   Utility.msgInf("GEOSTORE", "Non so cosa hai inserito");
                              }
                              break;
                         case 9:
                              Utility.msgInf("GEOSTORE", "Elenco oggetti per categoria\n\n");
                              cat = new Categoria();
                              view.maskObjViaCat(cat);
                              view.printOggetti(or.getOggettiViaCategoriaWithDB(cat.getNome()));
                              break;
                         case 10:
                              Utility.msgInf("GEOSTORE", "Elenco oggetti per materia\n\n");
                              m = new Materia();
                              view.maskObjViaMat(m);
                              view.printOggetti(or.getOggettiViaMateriaWithDB(m.getNome()));
                              break;
                         case 11:
                              Utility.msgInf("GEOSTORE", "Elenco oggetti dispobili\n\n");
                              view.printOggetti(or.getOggettiDispWithDB());
                              break;
                         case 12:
                              Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina categoria\n\n");
                              break;
                         case 13:
                              Utility.msgInf("GEOSTORE", "Crea/Modifica/Elimina materia\n\n");
                              break;
                         case 14:
                              Utility.msgInf("GEOSTORE", "Ordini totali giornalieri\n\n");
                              view.printOggetti(or.getOggettiDispWithDB());
                              break;
                         default:
                              Utility.msgErr("GEOSTORE", "Non so cosa hai inserito");
                              break;
                    }

                    if(Utility.insertString("\nVUOI ANDARE AVANTI?").equalsIgnoreCase("s")){
                         flag = true;
                         System.out.println("\n***VA BENE***");
                    }
                    else{
                         flag = false;
                         System.out.println("\n***OK COME VUOI***");
                    }
               }while(flag);

          }
          else{
               Utility.msgErr("GEOSTORE", "Non so cosa hai inserito\n\n");
          }

          Utility.msgInf("GEOSTORE","***GRAZIE PER AVER PROVATO IL MENU***");

     }
}
