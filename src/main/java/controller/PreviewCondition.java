package src.main.java.controller;

import src.main.java.utility.Utility;

public class PreviewCondition {

     public static void main(String[] args){

          String valueInput = "";
          int num = 0;
          boolean flag;

          do{
               valueInput = Utility.insertString("INSERISCI UNA STRINGA");
               switch(valueInput){
                    case "a":
                         Utility.msgInf("PROVA", "hai inserito a");
                         num = 43;
                         Utility.msgInf("PROVA", "per cui il numero è: " + num);
                         break;
                    case "b":
                         Utility.msgInf("PROVA", "hai inserito b");
                         num = 21;
                         Utility.msgInf("PROVA", "per cui il numero è: " + num);
                         break;
                    case "c":
                         Utility.msgInf("PROVA", "hai inserito c");
                         num = 1;
                         Utility.msgInf("PROVA", "per cui il numero è: " + num);
                         break;
                    default:
                         Utility.msgErr("PROVA", "non so cosa hai inserito");
                         break;
               }

               if(Utility.insertString("VUOI RIPROVARE?").equalsIgnoreCase("si")){
                    flag = true;
               }
               else{
                    flag = false;
               }
          }while(flag);

     }
}
