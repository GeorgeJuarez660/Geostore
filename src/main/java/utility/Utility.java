package src.main.java.utility;

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
                msgErr("GEOSTORE", "devi inserire un valore numerico");
                flag = true;
            }
            catch (Exception err){
                msgErr("GEOSTORE", "errore");
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
}
