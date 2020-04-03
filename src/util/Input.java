package util;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Input {

    //init scanner
    private static Scanner scanner;

    //scanner constructor
    public Input() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    //getString method w/ default prompt. returns input string.
    public String getString() {
        return getString("Please type a sentence: ");
    }

    //getString method w/ custom prompt. returns input string.
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    //yesNo method w/ default prompt. returns boolean T/F for Y/N.
    public boolean yesNo() {
        return yesNo("(y/n): ");
    }

    //yesNo method w/ custom prompt. returns boolean T/F for Y/N.
    public boolean yesNo(String prompt) {
        System.out.print(prompt);
        char response = scanner.nextLine().trim().toLowerCase().charAt(0);
//        if (Character.compare(response,'y') == 0) {
        if (response == 'y') {
            return true;
        } else if (response == 'n') {
            return false;
        } else {
            System.out.println("Please enter yes or no.");
            return yesNo(prompt);
        }
    }

    //getInt method w/ default prompt. returns input int between min & max.
    public int getInt(int x, int y) {

        return getInt(x, y, String.format("Please enter a number between %d and %d: ", x, y));

    }

    //getInt method w/ custom prompt. returns input int between min & max.
    public int getInt(int x, int y, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        int i;

        try {                                    //validation try/catch.
            i = Integer.parseInt(input.trim());
//            i = Integer.valueOf(input);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid format...");
            return getInt(x, y, prompt);
        }

        if (i < x || i > y) {
            System.out.println("Number out of range.");
            return getInt(x, y, prompt);
        }
        return i;
    }

    //getInt method w/ default prompt. returns input int.
    public int getInt() {
        return getInt("Please enter an integer: ");
    }


    //getInt method w/ custom prompt. returns input int.
    public int getInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid format...");
            return getInt(prompt);
        }
    }

    //getDouble method w/ default prompt. returns input double between min & max.
    public double getDouble(double x, double y) {
        return getDouble(x, y, String.format("Please enter a number between %f and %f: ", x, y));
    }

    //getDouble method w/ custom prompt. returns input double between min & max.
    public double getDouble(double x, double y, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        double d;

        try {
            d = Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid format...");
            return getDouble(x, y, prompt);
        }

        if (d < x || d > y) {
            System.out.println("Number out of range.");
            return getDouble(x, y);
        }
        return d;
    }

    //getDouble method w/ default prompt. returns input double.
    public double getDouble() {
        return getDouble("Please an decimal number: ");
    }

    //getDouble method w/ custom prompt. returns input double.
    public double getDouble(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid format...");
            return getDouble(prompt);
        }
    }

    public Integer getBinary() {
        return getBinary("Enter a binary number: ");
    }

    public Integer getBinary(String prompt) {
        String input = getString(prompt);

        try {
            return Integer.valueOf(input, 2);
        } catch (Exception e) {
            System.out.println("There was an exception...");
            return getBinary(prompt);
        }
    }

    public Integer getHex() {
        return getHex("Enter a Hexadecimal number: ");
    }

    public Integer getHex(String prompt) {
        String input = getString(prompt);

        try {
            return Integer.valueOf(input, 16);
        } catch (Exception e) {
            System.out.println("There was an exception...");
            return getHex(prompt);
        }
    }

    public String intToBin() {
        return intToBin("Enter an integer to convert to binary: ");
    }

    public String intToBin(String prompt) {
        int input = getInt(prompt);

        try {
            return Integer.toBinaryString(input);
        } catch (Exception e) {
            System.out.println("There was an exception...");
            return intToHex(prompt);
        }
    }

    public String intToHex() {
        return intToHex("Enter an integer to convert to hexadecimal: ");
    }

    public String intToHex(String prompt) {
        int input = getInt(prompt);

        try {
            return Integer.toHexString(input);
        } catch (Exception e) {
            System.out.println("There was an exception...");
            return intToHex(prompt);
        }
    }

    public boolean addCheck(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == k) {
                    return true;
                }
            }
        }
        return false;
    }
}
