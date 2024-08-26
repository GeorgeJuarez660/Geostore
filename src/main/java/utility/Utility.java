package src.main.java.utility;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Utility {

    static Scanner input = new Scanner(System.in);

    public static void msgInf(String owner, String text) //metodo di messaggio info
    {
        System.out.println(owner + ": " + text);
    }

    public static void msgErr(String owner, String text) //metodo di messaggio error
    {
        System.err.println(owner + ": " + text);
    }

    public static int insertInt(String title){
        System.out.println(title);
        int num = 0;
        boolean flag = false;

        do{ //controlla ripetutamente se è un numero
            try{
                num = Integer.parseInt(input.nextLine());
            }catch(NumberFormatException err){
                msgInf("GEOSTORE", "devi inserire un valore numerico");
                flag = true;
            }
            catch (Exception err){
                msgInf("GEOSTORE", "errore");
                flag = true;
            }
        }while(flag);

        return num;

    }

    public static BigDecimal insertBigDecimal(String title){
        System.out.println(title);
        BigDecimal num = new BigDecimal(0);
        boolean flag = false;

        do{ //controlla ripetutamente se è un numero
            try{
                num = new BigDecimal(input.nextLine());
            }catch(NumberFormatException err){
                msgInf("GEOSTORE", "devi inserire un valore decimale");
                flag = true;
            }
            catch (Exception err){
                msgInf("GEOSTORE", "errore");
                flag = true;
            }
        }while(flag);

        return num;

    }

    public static java.sql.Date convertDate(String date){
        // La stringa da convertire
        String dateString = date;

        // Specifica il formato della stringa (deve corrispondere al formato della stringa in input)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parsing della stringa in un oggetto java.util.Date
            Date parsedDate = dateFormat.parse(dateString);

            // Conversione dell'oggetto java.util.Date in un oggetto java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // Stampa del risultato
            System.out.println("java.sql.Date: " + sqlDate);

            return sqlDate;
        } catch (ParseException e) {
            // Gestione delle eccezioni nel caso la stringa non sia nel formato corretto
            e.printStackTrace();
        }

        return null;
    }

    public static String insertString(String title){
        System.out.println(title);
        String word = input.nextLine();

        return word;

    }
}
