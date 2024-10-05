package src.main.java.utility;

import src.main.java.model.CategoriaRepository;
import src.main.java.model.DisponibilitaRepository;
import src.main.java.model.MateriaRepository;
import src.main.java.model.StatusRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Utility {

    static Scanner input = new Scanner(System.in);

    static CategoriaRepository car = new CategoriaRepository();
    static MateriaRepository mr = new MateriaRepository();
    static DisponibilitaRepository dr = new DisponibilitaRepository();
    static StatusRepository sr = new StatusRepository();


    public static void msgInf(String owner, String text) //metodo di messaggio info
    {
        System.out.println(owner + ": " + text);
    }

    public static int insertInt(String title){
        System.out.println(title);
        int num = 0;
        boolean flag;

        do{ //controlla ripetutamente se è un numero
            flag = false;
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
        boolean flag;

        do{ //controlla ripetutamente se è un numero
            flag = false;
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

    public static String insertString(String title){
        System.out.println(title);
        String word = input.nextLine();

        return word;

    }

    public static String formatValueBigDecimal(BigDecimal value){
        String formattedValue = "";

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ITALIAN);
        DecimalFormat df = new DecimalFormat("###,##0.##", dfs);
        formattedValue = df.format(value);

        return formattedValue;
    }

    public static String formatValueInteger(Integer value){
        return formatValueBigDecimal(BigDecimal.valueOf(value));
    }
}
