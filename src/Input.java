import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner;

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        return scanner;
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static String getString(String prompt) {
        System.out.println(prompt);
        String out = getScanner().nextLine();
        return out;
    }

    static boolean getBoolean(String prompt) {
        while (true) {
            String in = getString(prompt);
            if (in.toLowerCase().equals("y")) {
                return true;
            } else if (in.toLowerCase().equals("n")) {
                return false;
            } else {
                System.out.println("That's not y or n!");
            }
        }
    }

    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int out = getScanner().nextInt();
                getScanner().nextLine();
                return out;
            } catch (InputMismatchException ex) {
                System.out.println("That's not an integer.");
            }
        }
    }
}
