package src.main.java.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Cliente {
    private Integer id = 0;
    private static Integer count=0;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String indirizzo;
    private String codeAdmin;

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCodeAdmin() {
        return codeAdmin;
    }

    public void setCodeAdmin(String codeAdmin) {
        this.codeAdmin = codeAdmin;
    }

    public Cliente() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cognome, cliente.cognome) && Objects.equals(email, cliente.email) && Objects.equals(telefono, cliente.telefono) && Objects.equals(indirizzo, cliente.indirizzo) && Objects.equals(codeAdmin, cliente.codeAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, email, telefono, indirizzo, codeAdmin);
    }

    public Cliente(Integer id, String nome, String cognome, String email, String telefono, String indirizzo, String codeAdmin) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.codeAdmin = codeAdmin;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", codeAdmin='" + codeAdmin + '\'' +
                '}';
    }
}
