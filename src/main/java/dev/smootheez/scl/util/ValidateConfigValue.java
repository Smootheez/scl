package dev.smootheez.scl.util;

public class ValidateConfigValue {

    /**
     * Validates if the provided string can be successfully parsed as an integer.
     *
     * @param input the string input to validate
     * @return true if the input string can be parsed as an integer, false otherwise
     */
    public static boolean validateIntValue(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the provided string can be successfully parsed as a double.
     *
     * @param input the string input to validate
     * @return true if the input string can be parsed as a double, false otherwise
     */
    public static boolean validateDoubleValue(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
