package utils;

import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getValidInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // Using Integer.parseInt catches empty lines better than scanner.nextInt()
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("--> Error: Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String getValidString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}