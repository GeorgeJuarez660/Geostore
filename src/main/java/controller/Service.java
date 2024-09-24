package src.main.java.controller;

import src.main.java.model.*;
import src.main.java.utility.Utility;
import src.main.java.view.View;

public class Service {

    View view = new View();
    ProdottoRepository pr = new ProdottoRepository();
    UtenteRepository ur = new UtenteRepository();
    CategoriaRepository car = new CategoriaRepository();
    MateriaRepository mr = new MateriaRepository();
    OrdineRepository odr = new OrdineRepository();
    DisponibilitaRepository dr = new DisponibilitaRepository();
    StatusRepository sr = new StatusRepository();


    public void profiloUtente(Utente u){
        view.printUtente(ur.getUtenteWithDB(u.getId()));
    }

    public void elencoUtenti(){
        view.printUtenti(ur.getUtentiWithDB());
    }

    public void creazioneUtente(){
        int csCliente = Utility.insertInt("Scegli 1 per cliente o 2 per amministratore");
        Utente u;
        boolean flagInsert;
        if(csCliente == 1){
            u = new Cliente();
            view.maskInsertUtente(u);
            do{
                String question = Utility.insertString("Vuoi procedere? (s/n)");
                if(question.equalsIgnoreCase("s")) {
                    int num = ur.insertUtenteWithDB(u.getId(), u);

                    if (num > 0) {
                        Utility.msgInf("GEOSTORE", "Nuovo cliente aggiunto\n");
                    } else {
                        Utility.msgInf("GEOSTORE", "Cliente non aggiunto\n");
                    }

                    flagInsert = false;
                }else if(question.equalsIgnoreCase("n")){
                    Utility.msgInf("GEOSTORE", "Operazione annullata\n");
                    flagInsert = false;
                }
                else{
                    Utility.msgInf("GEOSTORE", "Rileggi la domanda\n");
                    flagInsert = true;
                }
            }while(flagInsert);

        }
        else if(csCliente == 2){
            u = new Amministratore();
            view.maskInsertUtente(u);
            do{
                String question = Utility.insertString("Vuoi procedere? (s/n)");
                if(question.equalsIgnoreCase("s")) {
                    int num = ur.insertUtenteWithDB(u.getId(), u);

                    if (num > 0) {
                        Utility.msgInf("GEOSTORE", "Nuovo amministratore aggiunto\n");
                    } else {
                        Utility.msgInf("GEOSTORE", "Amministratore non aggiunto\n");
                    }

                    flagInsert = false;
                }else if(question.equalsIgnoreCase("n")){
                    Utility.msgInf("GEOSTORE", "Operazione annullata\n");
                    flagInsert = false;
                }
                else{
                    Utility.msgInf("GEOSTORE", "Rileggi la domanda\n");
                    flagInsert = true;
                }
            }while(flagInsert);
        }
    }

    public void modificaUtente(Utente u, boolean isAnotherUser){
        Utente foundU;
        if(isAnotherUser){
            view.printUtenti(ur.getUtentiWithDB());
            foundU = ur.getUtenteWithDB(Utility.insertInt("Inserisci l'id utente"));
        }
        else{
            foundU = ur.getUtenteWithDB(u.getId());
        }

        if(foundU != null && foundU.getNome() != null){
            Utility.msgInf("GEOSTORE", "Utente trovato\n");

            if(foundU instanceof Amministratore) { //se l'oggetto trovato contiene l'istanza di una classe figlia
                Amministratore aOld = (Amministratore) foundU;
                Amministratore aNew = (Amministratore) view.maskUpdateUtente(aOld, new Amministratore());

                int num = ur.updateUtenteWithDB(aNew.getId(), aNew);

                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Amministratore aggiornato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Amministratore non aggiornato\n");
                }
            }
            else{
                Cliente cOld = (Cliente) foundU;
                Cliente cNew = (Cliente) view.maskUpdateUtente(cOld, new Cliente());

                int num = ur.updateUtenteWithDB(cNew.getId(), cNew);

                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Cliente aggiornato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Cliente non aggiornato\n");
                }
            }

        }
        else{
            Utility.msgInf("GEOSTORE", "Utente non trovato\n");
        }
    }

    public void eliminazioneUtente(){
        view.printUtenti(ur.getUtentiWithDB());
        Utente u;
        u = ur.getUtenteWithDB(Utility.insertInt("Inserisci l'id utente"));

        if(u != null && u.getNome() != null){
            Utility.msgInf("GEOSTORE", "Utente trovato\n");
            if(Utility.insertString("Sei sicuro di voler eliminare questo utente?").equalsIgnoreCase("s")){
                int num = ur.deleteUtenteWithDB(u.getId());

                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Utente eliminato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Utente non eliminato\n");
                }

            }
            else{
                Utility.msgInf("GEOSTORE", "Operazione annullata\n");
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "Utente non trovato\n");
        }
    }

    public void creazioneProdotto(){
        Prodotto p = new Prodotto();
        view.maskInsertProdotto(p);

        Categoria cat = car.getCategoriaWithDB(p.getCategoria().getId());
        Materia m = mr.getMateriaWithDB(p.getMateria().getId());
        Disponibilita d = dr.getDisponibilitaWithDB(p.getDisponibilita().getId());

        if(cat != null && m != null && d != null && cat.getNome() != null && m.getNome() != null && d.getCode() != null){
            p.setCategoria(cat);
            p.setMateria(m);
            p.setDisponibilita(d);
            int num = pr.insertProdottoWithDB(p.getId(), p);

            if(num > 0){
                Utility.msgInf("GEOSTORE", "Nuovo prodotto aggiunto\n");
            }
            else{
                Utility.msgInf("GEOSTORE", "Prodotto non aggiunto\n");
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "Categoria e/o Materia inesistenti\n");
        }
    }

    public void modificaProdotto(){
        view.printProdotti(pr.getProdottiWithDB());
        Prodotto p = pr.getProdottoWithDB(Utility.insertInt("Inserisci l'id prodotto"));

        if(p != null && p.getNome() != null){
            Utility.msgInf("GEOSTORE", "Prodotto trovato\n");

            Prodotto pNew = view.maskUpdateProdotto(p, new Prodotto());

            Categoria cat = car.getCategoriaWithDB(pNew.getCategoria().getId());
            Materia m = mr.getMateriaWithDB(pNew.getMateria().getId());
            Disponibilita d = dr.getDisponibilitaWithDB(pNew.getDisponibilita().getId());

            if(cat != null && m != null && d != null && cat.getNome() != null && m.getNome() != null && d.getCode() != null){
                pNew.setCategoria(cat);
                pNew.setMateria(m);
                pNew.setDisponibilita(d);
                int num = pr.updateProdottoWithDB(pNew.getId(), pNew);

                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Prodotto aggiornato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Prodotto non aggiornato\n");
                }
            }
            else{
                Utility.msgInf("GEOSTORE", "Categoria e/o Materia inesistenti\n");
            }

        }
        else{
            Utility.msgInf("GEOSTORE", "Prodotto non trovato\n");
        }
    }

    public void eliminazioneProdotto(){
        view.printProdotti(pr.getProdottiWithDB());
        Prodotto p = pr.getProdottoWithDB(Utility.insertInt("Inserisci l'id prodotto"));

        if(p != null && p.getNome() != null){
            Utility.msgInf("GEOSTORE", "Prodotto trovato\n");
            if(Utility.insertString("Sei sicuro di voler eliminare questo prodotto?").equalsIgnoreCase("s")){
                int num = pr.deleteProdottoWithDB(p.getId());

                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Prodotto eliminato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Prodotto non eliminato\n");
                }

            }
            else{
                Utility.msgInf("GEOSTORE", "Operazione annullata\n");
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "Prodotto non trovato\n");
        }
    }

    public void ordinazioneProdotto(Utente u){
        Ordine o;
        Prodotto p;
        view.printProdotti(pr.getProdottiDispWithDB());
        o = new Ordine();
        view.maskInsertOrdine(o, u);
        p = pr.getProdottoDispWithDB(o.getProdotto().getId());

        if(p != null && p.getNome() != null){
            Utility.msgInf("GEOSTORE", "Il prodotto è disponibile\n");
            o.setProdotto(p);

            if(o.getQuantita() <= p.getQuantita_disp()){
                o.setPrezzo_unitario(p.getPrezzo());

                int num = odr.insertOrdineWithDB(null, o);
                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Ordine effettuato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Ordine non effettuato\n");
                }
            }
            else{
                Utility.msgInf("GEOSTORE", "La quantità ordinata supera quella disponibile\n");
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "L'oggetto ordinato non è disponibile oppure è inesistente\n");
        }
    }

    public void modificaOrdine(){
        view.printOrdini(odr.getOrdiniWithDB());
        Ordine o = odr.getOrdineWithDB(Utility.insertInt("Inserisci l'id ordine"));

        if(o != null && o.getProdotto() != null && o.getProdotto().getNome() != null){
            Utility.msgInf("GEOSTORE", "Ordine trovato\n");

            Ordine oNew = view.maskUpdateOrdine(o, new Ordine());

            Stato s = sr.getStatoWithDB(oNew.getStato().getId());

            if(s != null && s.getCode() != null){
                changeStatusProdottoAfterOrder(o, oNew);

                int num = odr.updateOrdineWithDB(oNew.getId(), oNew);

                if(num > 0){
                    Utility.msgInf("GEOSTORE", "Ordine aggiornato\n");
                }
                else{
                    Utility.msgInf("GEOSTORE", "Ordine non aggiornato\n");
                }
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "Ordine non trovato\n");
        }
    }

    private static void changeStatusProdottoAfterOrder(Ordine oOld, Ordine oNew){
        if(oOld.getStato().getId() == 1 && oNew.getStato().getId() == 2){
            Prodotto p;
            ProdottoRepository pr = new ProdottoRepository();
            p = pr.getProdottoWithDB(oOld.getProdotto().getId());

            Integer subQuantita = p.getQuantita_disp() - oNew.getQuantita();
            p.setQuantita_disp(subQuantita);
            Disponibilita newDisp = new Disponibilita();

            if(p.getQuantita_disp() == 0){
                newDisp.setId(4);
            }
            else if(p.getQuantita_disp() >= 1 && p.getQuantita_disp() <=3){
                newDisp.setId(3);
            }
            else{
                newDisp.setId(1);
            }
            p.setDisponibilita(newDisp);

            int num = pr.updateProdottoAfterAccOrdineWithDB(p.getId(), p);

            if(num > 0){
                Utility.msgInf("GEOSTORE", "Quantità e/o disponibilità aggiornati\n");
            }
            else{
                Utility.msgInf("GEOSTORE", "Quantità e/o disponibilità non aggiornati\n");
            }
        }
    }

    public void eliminazioneOrdine(Utente u, boolean onlyOwnUser){
        Ordine o;
        if(onlyOwnUser){
            view.printOrdini(odr.getOrdiniByUserWithDB(u.getId()));
            o = odr.getOrdineByUserWithDB(u.getId(), Utility.insertInt("Inserisci l'id ordine"));

        }
        else{
            view.printOrdini(odr.getOrdiniWithDB());
            o = odr.getOrdineWithDB(Utility.insertInt("Inserisci l'id ordine"));
        }

        if(o != null && o.getProdotto() != null && o.getProdotto().getNome() != null){
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
    }

    public void elencoProdotti(){
        view.printProdotti(pr.getProdottiWithDB());
    }

    public void creazioneCategoria(){
        Categoria cat = new Categoria();
        view.maskInsertCategoria(cat);
        car.insertCategoriaWithDB(cat.getId(), cat);
        Utility.msgInf("GEOSTORE", "Nuova categoria aggiunta\n");
    }

    public void modificaCategoria(){
        view.printCategorie(car.getCategorie());
        Categoria cat = car.getCategoriaWithDB(Utility.insertInt("Inserisci l'id categoria"));

        if(cat != null && cat.getNome() != null){
            Utility.msgInf("GEOSTORE", "Categoria trovata\n");

            Categoria cNew = view.maskUpdateCategoria(cat, new Categoria());

            int num = car.updateCategoriaWithDB(cNew.getId(), cNew);

            if(num > 0){
                Utility.msgInf("GEOSTORE", "Categoria aggiornata\n");
            }
            else{
                Utility.msgInf("GEOSTORE", "Categoria non aggiornata\n");
            }

        }
        else{
            Utility.msgInf("GEOSTORE", "Categoria non trovata\n");
        }
    }

    public void eliminazioneCategoria(){
        view.printCategorie(car.getCategorieWithDB());
        Categoria cat = car.getCategoriaWithDB(Utility.insertInt("Inserisci l'id categoria"));

        if(cat != null && cat.getNome() != null){
            Utility.msgInf("GEOSTORE", "Categoria trovata\n");
            if(Utility.insertString("Sei sicuro di voler eliminare questa categoria?").equalsIgnoreCase("s")){
                car.deleteCategoriaWithDB(cat.getId());
                Utility.msgInf("GEOSTORE", "Categoria eliminata\n");
            }
            else{
                Utility.msgInf("GEOSTORE", "Operazione annullata\n");
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "Categoria non trovata\n");
        }
    }

    public void prodottiViaCategoria(){
        view.printCategorie(car.getCategorieWithDB());
        Categoria cat = new Categoria();
        view.maskObjViaCat(cat);
        view.printProdotti(pr.getProdottiViaCategoriaWithDB(cat.getId()));
    }

    public void creazioneMateria(){
        Materia m = new Materia();
        view.maskInsertMateria(m);
        mr.insertMateriaWithDB(m.getId(), m);
        Utility.msgInf("GEOSTORE", "Nuova materia aggiunta\n");
    }

    public void modificaMateria(){
        view.printMaterie(mr.getMaterieWithDB());
        Materia m = mr.getMateriaWithDB(Utility.insertInt("Inserisci l'id materia"));

        if(m != null && m.getNome() != null){
            Utility.msgInf("GEOSTORE", "Materia trovata\n");

            Materia mNew = view.maskUpdateMateria(m, new Materia());

            int num = mr.updateMateriaWithDB(mNew.getId(), mNew);

            if(num > 0){
                Utility.msgInf("GEOSTORE", "Materia aggiornata\n");
            }
            else{
                Utility.msgInf("GEOSTORE", "Materia non aggiornata\n");
            }

        }
        else{
            Utility.msgInf("GEOSTORE", "Materia non trovata\n");
        }
    }

    public void eliminazioneMateria(){
        view.printMaterie(mr.getMaterieWithDB());
        Materia m = mr.getMateriaWithDB(Utility.insertInt("Inserisci l'id materia"));

        if(m != null && m.getNome() != null){
            Utility.msgInf("GEOSTORE", "Materia trovata\n");
            if(Utility.insertString("Sei sicuro di voler eliminare questa materia?").equalsIgnoreCase("s")){
                mr.deleteMateriaWithDB(m.getId());
                Utility.msgInf("GEOSTORE", "Materia eliminata\n");
            }
            else{
                Utility.msgInf("GEOSTORE", "Operazione annullata\n");
            }
        }
        else{
            Utility.msgInf("GEOSTORE", "Materia non trovata\n");
        }
    }

    public void prodottiViaMateria(){
        view.printMaterie(mr.getMaterieWithDB());
        Materia m = new Materia();
        view.maskObjViaMat(m);
        view.printProdotti(pr.getProdottiViaMateriaWithDB(m.getId()));
    }

    public void ordiniEffettuati(Utente u, boolean youCanChoose){
        int choose = 0;
        if(youCanChoose){
            choose = Utility.insertInt("1 - tuoi ordini, 2 - di un'altra persona, 3 - generale");
        }

        if(choose == 1 || !youCanChoose){
            Utility.msgInf("GEOSTORE", "Tuoi ordini\n");
            view.printOrdini(odr.getOrdiniByUserWithDB(u.getId()));
        }
        else if(choose == 2){
            Utility.msgInf("GEOSTORE", "Ordini di qualcun'altro\n");

            view.printUtenti(ur.getUtentiWithDB());
            Integer anotherUser = Utility.insertInt("Inserisci l'id di un'altra persona");
            view.printOrdini(odr.getOrdiniByUserWithDB(anotherUser));
        }
        else if(choose == 3){
            Utility.msgInf("GEOSTORE", "Ordini in generale\n");

            view.printOrdini(odr.getOrdiniWithDB());
        }
        else{
            Utility.msgInf("GEOSTORE", "Non so cosa hai inserito\n");
        }
    }

    public void prodottiDisponibili(){
        view.printProdotti(pr.getProdottiDispWithDB());
    }

    public void ordiniTotaliGiornalieri(Utente u){
        String chooseDate = Utility.insertString("Inserisci la data in formato yyyy-mm-dd");
        view.printOrdiniTotGior(odr.getOrdineTotGiorWithDB(u, chooseDate));
    }
}
