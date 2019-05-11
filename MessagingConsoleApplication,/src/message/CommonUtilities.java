package message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class CommonUtilities {
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if ((value instanceof String)) {
            return ((String) value).trim().length() == 0;
        }
        if ((value instanceof Collection)) {
            return ((Collection) value).isEmpty();
        }
        return (value.getClass().isArray()) && (Array.getLength(value) == 0);
    }


    public static void printUsersInTableForm(ArrayList<User> users) {
        int index = 1;
        printStringMultiple("-", 180);
        System.out.println();
        System.out.printf("%10s %30s %30s %30s %30s  %30s", "N", "Name", "Surname", "Username", "Email", "Role");
        System.out.println();
        printStringMultiple("-", 180);
        System.out.println();
        for (User user : users) {
            System.out.format("%10s %30s %30s %30s %30s %30s  ", index, user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getRole());
            System.out.println();
            index += 1;
        }
    }

    public static void validateScannerNumber(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Παρακαλώ δώστε αριθμό");
            scanner.next();
        }
    }

    public static void printUsersInTableForm(User user) {
        printStringMultiple("-", 180);
        System.out.println();
        System.out.printf("%30s %30s %30s %30s %30s", "Name", "Surname", "Username", "Email", "Role");
        System.out.println();
        printStringMultiple("-", 180);
        System.out.println();
        System.out.format("%30s %30s %30s %30s %30s", user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getRole());
        System.out.println();
    }

    public static void printMessagesInTableForm(ArrayList<Message> messages, String collumnName) {
        int index = 1;
        printStringMultiple("-", 80);
        System.out.println();
        System.out.printf("%10s %30s %30s", "N", "dateOfSubmision", collumnName);
        System.out.println();
        printStringMultiple("-", 80);
        System.out.println();
        for (Message message : messages) {
            System.out.format("%10s %30s %30s ", index, message.getDateOfSubmision(), message.getSender().getEmail());
            System.out.println();
            index += 1;
        }
    }

    public static void printStringMultiple(String word, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(word);
        }
    }

    public static void printMessagesInTableForm(ArrayList<Message> messages) {
        int index = 1;
        printStringMultiple("-", 180);
        System.out.println();
        System.out.printf("%10s %30s %30s %30s %30s ", "N", "Sender", "Receiver", "dateOfSubmission", "body");
        System.out.println();
        printStringMultiple("-", 180);
        System.out.println();
        for (Message message : messages) {
            System.out.format("%10s %30s %30s %30s  %30s  ", index, message.getSender().getEmail(), message.getReceiver().getEmail(), message.getDateOfSubmision(), message.getBody());
            System.out.println();
            index += 1;
        }
    }

    public static Integer scannerReadNumber(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Παρακαλώ δώστε αριθμό");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static Integer scannerReadNumber(Scanner scanner, int min, int max) {
        int option = 0;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Παρακαλώ δώστε αριθμό");
                scanner.next();
            }
            option = scanner.nextInt();
            if (option < min || option > max) {
                System.out.println("Ο αριθμός που δώσατε δεν αντιστοιχεί σε επιλογή.Δώστε νέα επιλογή");
            }
        } while (option < min || option > max);
        return option;
    }


}