package src.main.java.utility;

import src.main.java.model.Categoria;

import java.util.HashMap;

public interface CRUD {

    //Interfaccia dove dichiaro metodi astratti che mi serviranno per andare a implementarli nella classe CategoriaRepository

    public void insertCategoria(Integer id, Categoria c);
    public HashMap<Integer, Categoria> getCategorie();
    public Categoria getCategoria(String nome);
    public void updateCategoria(Integer id, Categoria newC);

}
