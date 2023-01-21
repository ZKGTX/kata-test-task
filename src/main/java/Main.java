import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;
        try {
            input = reader.readLine();
            while (!input.equals("exit")) {
                output = calc(input);
                System.out.println(output);
                input = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String calc (String input) throws Exception {
        String[] inputs = input.split(" ");
        String result;
        if (inputs.length != 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию");
        }
        if (inputs[1].matches("[^-+/*]")) {
            throw new Exception("строка не является математической операцией");
        }
        if (inputs[0].matches("[0-9]+") && inputs[2].matches("[0-9]+")) {
            result = calcArabic(inputs);
        }
        else if (inputs[0].matches("[IVX]+") && inputs[2].matches("[IVX]+")) {
            result = calcRoman(inputs);
        }
        else throw new Exception("формат математической операции не удовлетворяет заданию");

        return result;
    }

    public static String calcArabic(String[] input) throws Exception {
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[2]);
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("калькулятор принимает на вход числа от 1 до 10 включительно, не более");
        }
        return switch (input[1]) {
            case "+" -> String.valueOf(a + b);
            case "-" -> String.valueOf(a - b);
            case "*" -> String.valueOf(a * b);
            default -> String.valueOf(a / b);
        };
    }

    public static String calcRoman(String[] input) throws Exception {
        int a = romanToArabicConverter(input[0]);
        int b = romanToArabicConverter(input[2]);
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("калькулятор принимает на вход числа от I до X включительно, не более");
        }
        int result = switch (input[1]) {
            case "+" -> (a + b);
            case "-" -> (a - b);
            case "*" -> (a * b);
            default -> (a / b);
        };
        if (result < 0) {
            throw new Exception("в римской системе нет отрицательных чисел");
        }
        return arabicToRomanConverter(result);
    }

    public static int romanToArabicConverter(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }
    public static String arabicToRomanConverter(int arabic) {
        if (arabic == 0) {
            return "N";
        }
        int[] arabicNumbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumbers = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < arabicNumbers.length; i++) {
            while (arabic >= arabicNumbers[i]) {
                arabic = arabic - arabicNumbers[i];
                result.append(romanNumbers[i]);
            }
        }
        return result.toString();
    }
}
