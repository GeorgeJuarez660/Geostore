package src.main.java.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Disponibilita {
    private Integer id = 0;
    private static Integer count=0;
    private String code;
    private String descrizione;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Disponibilita() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disponibilita ordine1 = (Disponibilita) o;
        return Objects.equals(id, ordine1.id) && Objects.equals(code, ordine1.code) && Objects.equals(descrizione, ordine1.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, descrizione);
    }

    public Disponibilita(Integer id, String code, String descrizione) {
        this.id = id;
        this.code = code;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    public String fromIntToString(int value){
        String valueS = "";
        if(value == 1){
            valueS = "DISPONIBILE";
        }
        else if(value == 2){
            valueS = "ARRIVO";
        }
        else if(value == 3){
            valueS = "ESAURIMENTO";
        }
        else if(value == 4){
            valueS = "ESAURITO";
        }
        else if(value == 5){
            valueS = "N/A";
        }
        else{
            valueS = null;
        }

        return valueS;
    }

    public int fromStringToInt(String value){
        int valueInt;
        if(value.equalsIgnoreCase("DISPONIBILE")){
            valueInt = 1;
        }
        else if(value.equalsIgnoreCase("ARRIVO")){
            valueInt = 2;
        }
        else if(value.equalsIgnoreCase("ESAURIMENTO")){
            valueInt = 3;
        }
        else if(value.equalsIgnoreCase("ESAURITO")){
            valueInt = 4;
        }
        else if(value.equalsIgnoreCase("N/A")){
            valueInt = 5;
        }
        else{
            valueInt = 0;
        }

        return valueInt;
    }
}
