package src.main.java.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Ordine {
    private Integer id = 0;
    private static Integer count=0;
    private Cliente cliente;
    private Oggetto oggetto;
    private Date data_ordine;
    private Integer quantita;
    private BigDecimal prezzo_unitario;
    private String stato;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public BigDecimal getPrezzo_unitario() {
        return prezzo_unitario;
    }

    public void setPrezzo_unitario(BigDecimal prezzo_unitario) {
        this.prezzo_unitario = prezzo_unitario;
    }

    public Date getData_ordine() {
        return data_ordine;
    }

    public void setData_ordine(Date data_ordine) {
        this.data_ordine = data_ordine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Ordine() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine1 = (Ordine) o;
        return Objects.equals(id, ordine1.id) && Objects.equals(cliente, ordine1.cliente) && Objects.equals(oggetto, ordine1.oggetto) && Objects.equals(quantita, ordine1.quantita) && Objects.equals(prezzo_unitario, ordine1.prezzo_unitario) && Objects.equals(data_ordine, ordine1.data_ordine) && Objects.equals(stato, ordine1.stato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, oggetto, quantita, prezzo_unitario, data_ordine, stato);
    }

    public Ordine(Integer id, Cliente cliente, Oggetto oggetto, Integer quantita, BigDecimal prezzo_unitario, Date data_ordine, String stato) {
        this.id = id;
        this.cliente = cliente;
        this.oggetto = oggetto;
        this.quantita = quantita;
        this.prezzo_unitario = prezzo_unitario;
        this.data_ordine = data_ordine;
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", oggetto='" + oggetto + '\'' +
                ", quantita=" + quantita +
                ", prezzo_unitario=" + prezzo_unitario +
                ", data_ordine=" + data_ordine +
                ", stato=" + stato +
                '}';
    }
}
