package src.main.java.model;

import java.util.Objects;

public class Stato {
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

    public Stato() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stato ordine1 = (Stato) o;
        return Objects.equals(id, ordine1.id) && Objects.equals(code, ordine1.code) && Objects.equals(descrizione, ordine1.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, descrizione);
    }

    public Stato(Integer id, String code, String descrizione) {
        this.id = id;
        this.code = code;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Stato{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    public String fromIntToString(int value){
        String valueS = "";
        if(value == 1){
            valueS = "ELABORAZIONE";
        }
        else if(value == 2){
            valueS = "ACCETTATO";
        }
        else if(value == 3){
            valueS = "RIFIUTATO";
        }
        else{
            valueS = null;
        }

        return valueS;
    }

    public int fromStringToInt(String value){
        int valueInt;
        if(value.equalsIgnoreCase("ELABORAZIONE")){
            valueInt = 1;
        }
        else if(value.equalsIgnoreCase("ACCETTATO")){
            valueInt = 2;
        }
        else if(value.equalsIgnoreCase("RIFIUTATO")){
            valueInt = 3;
        }
        else{
            valueInt = 0;
        }

        return valueInt;
    }
}
