package defiantpvp.searlie.utilities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class numberManager {    // Map to hold Roman numeral values

    // Map to hold Roman numeral values
    private static final Map<Character, Integer> romanToIntMap = new HashMap<>();
    static {
        romanToIntMap.put('I', 1);
        romanToIntMap.put('V', 5);
        romanToIntMap.put('X', 10);
        romanToIntMap.put('L', 50);
        romanToIntMap.put('C', 100);
        romanToIntMap.put('D', 500);
        romanToIntMap.put('M', 1000);
    }

    // Method to convert Roman numeral to integer
    public static int romanToInt(String roman) {
        int total = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            int value = romanToIntMap.get(c);

            if (value < prevValue) {
                total -= value;
            } else {
                total += value;
            }
            prevValue = value;
        }
        return total;
    }

    // Method to convert integer to Roman numeral
    public static String intToRoman(int num) {
        if (num <= 0 || num > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999.");
        }

        // Map of Roman numerals in descending order
        Map<Integer, String> intToRomanMap = new LinkedHashMap<>();
        intToRomanMap.put(1000, "M");
        intToRomanMap.put(900, "CM");
        intToRomanMap.put(500, "D");
        intToRomanMap.put(400, "CD");
        intToRomanMap.put(100, "C");
        intToRomanMap.put(90, "XC");
        intToRomanMap.put(50, "L");
        intToRomanMap.put(40, "XL");
        intToRomanMap.put(10, "X");
        intToRomanMap.put(9, "IX");
        intToRomanMap.put(5, "V");
        intToRomanMap.put(4, "IV");
        intToRomanMap.put(1, "I");

        StringBuilder roman = new StringBuilder();

        // Build the Roman numeral string
        for (Map.Entry<Integer, String> entry : intToRomanMap.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();

            while (num >= value) {
                roman.append(symbol);
                num -= value;
            }
        }

        return roman.toString();
    }

    // Method to extract Roman numeral from input string and convert it to integer
    public static int extractAndConvert(String input) {
        // Extract the Roman numeral part from the string
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Input string must contain at least one space separating the words.");
        }
        String romanNumeral = parts[parts.length - 1];

        // Convert Roman numeral to integer
        return romanToInt(romanNumeral);
    }

    public static String removeColorCodes(String name) {
        // Remove color codes from Minecraft item names
        return name.replaceAll("(?i)§[0-9a-fk-or]", "");
    }

    public static String removeRomanNumeral(String name){
        // Define a regex pattern for Roman numerals
        String romanNumeralPattern = "\\b(I{1,3}|IV|V{1,3}|IX|X{1,3}|XL|L{1,3}|XC|C{1,3}|CD|D{1,3}|CM|M{1,3})\\b";

        // Compile the pattern
        Pattern pattern = Pattern.compile(romanNumeralPattern);
        Matcher matcher = pattern.matcher(name);

        // Remove all Roman numerals from the name
        return matcher.replaceAll("").trim();
    }

    public static String extractNumber(String text){
        return Pattern.compile("\\d+")
                .matcher(text)
                .results()
                .map(match -> match.group())
                .findFirst()
                .orElse("No number found");
    }
}
