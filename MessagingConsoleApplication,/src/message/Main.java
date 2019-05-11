package message;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            Actions actions = new Actions();
            Integer option=0;
            int   number=0;
            do {
                try {
                    System.out.println("παρακαλω επιλεξτε μια επιλογη απο το μενου για να κανετε τιν αντιστιχη ενεργεια");
                    System.out.println("1 για να συνδεθέιτε");
                    System.out.println("2 για να κλείσετε το πρόγραμμα");
                    CommonUtilities.validateScannerNumber(scanner);
                    option = scanner.nextInt();

                    //login
                    if(option.equals(1)){
                        actions.loginAction();
                        //exit
                    }else if(option.equals(2)){
                        System.exit(0);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (option !=2);
        }}