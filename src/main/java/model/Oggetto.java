package src.main.java.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Oggetto {
    private Integer id = 0;
    private static Integer count=0;
    private String nome;
    private BigDecimal prezzo;
    private Disponibilita disponibilita;
    private String materia;
    private Categoria categoria;
    private Integer quantita_disp;

    public Integer getId() {
        return id;
    }

    public void setCount() {
        count++;
        id = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Disponibilita getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Disponibilita disponibilita) {
        this.disponibilita = disponibilita;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantita_disp() {
        return quantita_disp;
    }

    public void setQuantita_disp(Integer quantita_disp) {
        this.quantita_disp = quantita_disp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oggetto oggetto = (Oggetto) o;
        return Objects.equals(id, oggetto.id) && Objects.equals(nome, oggetto.nome) && Objects.equals(prezzo, oggetto.prezzo) && Objects.equals(disponibilita, oggetto.disponibilita) && Objects.equals(materia, oggetto.materia) && Objects.equals(categoria, oggetto.categoria) && Objects.equals(quantita_disp, oggetto.quantita_disp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, prezzo, disponibilita, materia, categoria, quantita_disp);
    }

    public Oggetto() {
    }

    public Oggetto(Integer id, String nome, BigDecimal prezzo, Disponibilita disponibilita, String materia, Categoria categoria, Integer quantita_disp) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.disponibilita = disponibilita;
        this.materia = materia;
        this.categoria = categoria;
        this.quantita_disp = quantita_disp;
    }

    @Override
    public String toString() {
        return "Oggetto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", disponibilita='" + disponibilita + '\'' +
                ", materia='" + materia + '\'' +
                ", categoria='" + categoria + '\'' +
                ", quantita_disp=" + quantita_disp +
                '}';
    }
}
