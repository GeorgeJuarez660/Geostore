package src.main.java.utility;

import src.main.java.model.Categoria;

import java.util.HashMap;

public interface categorieCRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe CategoriaRepository

    //metodi override per operazioni CRUD locali
    public void insertCategoria(Integer id, Categoria c);
    public HashMap<Integer, Categoria> getCategorie();
    public Categoria getCategoria(String nome);
    public void updateCategoria(Integer id, Categoria newC);
    public boolean deleteCategoria(Integer id);

    //metodi override per operazioni CRUD con database
    public void insertCategoriaWithDB(Integer id, Categoria c);
    public HashMap<Integer, Categoria> getCategorieWithDB();
    public Categoria getCategoriaWithDB(String nome);
    public void updateCategoriaWithDB(Integer id, Categoria newC);
    public void deleteCategoriaWithDB(Integer id);

}
