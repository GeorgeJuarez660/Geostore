package src.main.java.utility;

import src.main.java.model.Disponibilita;
import src.main.java.model.Materia;

import java.util.HashMap;

public interface materieCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe MateriaRepository

    //metodi override per operazioni CRUD con database
    public void insertMateriaWithDB(Integer id, Materia m);
    public HashMap<Integer, Materia> getMaterieWithDB();
    public Materia getMateriaWithDB(String nome);
    public int updateMateriaWithDB(Integer id, Materia newM);
    public void deleteMateriaWithDB(Integer id);

}
