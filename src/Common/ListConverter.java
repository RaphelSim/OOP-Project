package Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code ListConverter} class provides utility methods for converting
 * between strings and lists of strings, as well as replacing specific
 * characters within those strings.
 */
public class ListConverter {

    /**
     * Replaces all occurrences of '~' with ',' in the input string.
     *
     * @param input the string in which to replace characters
     * @return the modified string with '~' replaced by ',' or null if input is null
     */
    public static String replaceWithComma(String input) {
        if (input == null) {
            return null; // Handle null input gracefully
        }
        return input.replace("~", ",");
    }

    /**
     * Replaces all occurrences of ',' with '~' in the input string.
     *
     * @param input the string in which to replace characters
     * @return the modified string with ',' replaced by '~' or null if input is null
     */
    public static String replaceWithCurly(String input) {
        if (input == null) {
            return null; // Handle null input gracefully
        }
        return input.replace(",", "~");
    }

    /**
     * Replaces all occurrences of '~' with ',' in a list of strings.
     *
     * @param inputs the list of strings in which to replace characters
     * @return a new list with all '~' replaced by ',' in each string
     */
    public static List<String> replaceWithComma(List<String> inputs) {
        List<String> result = new ArrayList<>(); // Create a new list to store results
        for (String input : inputs) {
            result.add(replaceWithComma(input)); // Replace and add to result list
        }
        return result; // Return the new list
    }

    /**
     * Replaces all occurrences of ',' with '~' in a list of strings.
     *
     * @param inputs the list of strings in which to replace characters
     * @return a new list with all ',' replaced by '~' in each string
     */
    public static List<String> replaceWithCurly(List<String> inputs) {
        List<String> result = new ArrayList<>(); // Create a new list to store results
        for (String input : inputs) {
            result.add(replaceWithCurly(input)); // Replace and add to result list
        }
        return result; // Return the new list
    }

    /**
     * Converts a string to a list of strings by splitting it using ';'.
     *
     * @param input the string to be converted into a list
     * @return a list of strings obtained by splitting the input, or an empty list if input is null or empty
     */
    public static List<String> stringToList(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>(); // Return an empty list for null or empty input
        }
        List<String> list = new ArrayList<>(Arrays.asList(input.split(";")));
        list = replaceWithComma(list);
        return list; // Split and convert to a List
    }

    /**
     * Converts a list of strings into a single string joined by ';'.
     *
     * @param inputs the list of strings to be joined into a single string
     * @return a single string containing all elements joined by ';', or an empty string if the list is null or empty
     */
    public static String listToString(List<String> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            return ""; // Return an empty string for null or empty list
        }
        String string = String.join(";", inputs);
        string = replaceWithCurly(string);
        return string; // Join the list elements with ';'
    }
}