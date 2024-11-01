package Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListConverter {

    // Static method to replace all '~' with ',' and return the converted String
    public static String replaceWithComma(String input) {
        if (input == null) {
            return null; // Handle null input gracefully
        }
        return input.replace("~", ",");
    }

    // Static method to replace all ',' with '~' and return the converted String
    public static String replaceWithCurly(String input) {
        if (input == null) {
            return null; // Handle null input gracefully
        }
        return input.replace(",", "~");
    }

    // Static method to replace all '~' with ',' in a list of strings
    public static List<String> replaceWithComma(List<String> inputs) {
        List<String> result = new ArrayList<>(); // Create a new list to store results
        for (String input : inputs) {
            result.add(replaceWithComma(input)); // Replace and add to result list
        }
        return result; // Return the new list
    }

    // Static method to replace all ',' with '~' in a list of strings
    public static List<String> replaceWithCurly(List<String> inputs) {
        List<String> result = new ArrayList<>(); // Create a new list to store results
        for (String input : inputs) {
            result.add(replaceWithCurly(input)); // Replace and add to result list
        }
        return result; // Return the new list
    }

    // Static method to convert a string to a list of strings by splitting with ';'
    public static List<String> stringToList(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>(); // Return an empty list for null or empty input
        }
        List<String> list = new ArrayList<>(Arrays.asList(input.split(";")));
        list = replaceWithComma(list);
        return list; // Split and convert to a List
    }

    // Static method to convert a list of strings to a single string joined by ';'
    public static String listToString(List<String> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            return ""; // Return an empty string for null or empty list
        }
        String string = String.join(";", inputs);
        string = replaceWithCurly(string);
        return string; // Join the list elements with ';'
    }

}
