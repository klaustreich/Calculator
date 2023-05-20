
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Данный калькулятор для работы с римсикими и целыми арабскими числами от 1 до 10");
        System.out.println("Вы можете использовать операции: +, -, *, /");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Введите выражение, которое нужно вычислить. Используйте пробелы в качестве разделителя");
            System.out.println("Например: 1 + 1 или II - I");

            String input = sc.nextLine();

            if (input.equals("exit")) {
                System.out.println("Работа завершена");
                break;
            }
            if (input.length() < 3) throw  new Exception("Должно быть введено 2 операнда и 1 операция");
            System.out.println("Результат: " + calc(String.valueOf(input)));
        }
        sc.close();
    }
    public static String calc(String input) throws Exception {
        String result = "";
        int operand1;
        int operand2;
        boolean isRoman = false;
        boolean isArabic = false;
        String [] operands = input.split(" ");
        try {
            operand1 = Integer.parseInt(operands[0]);
            isArabic = true;
        } catch (Exception e) {
            operand1 = Roman.valueOf(operands[0]).toInt();
            isRoman = true;
        }
        try {
            operand2 = Integer.parseInt(operands[2]);
            isArabic = true;
        } catch (Exception e) {
            operand2 = Roman.valueOf(operands[2]).toInt();
            isRoman = true;
        }

        if (operand1 < 1 | operand2 > 10) throw  new Exception("Введите целые числа от 1 до 10");
        if (operands.length > 3) throw new Exception("Используйте только 2 аргумента и 1 операцию");

        switch (operands[1]) {
            case "+":
                result = Integer.toString(operand1 + operand2);
                break;
            case "-":
                result = Integer.toString(operand1 - operand2);
                break;
            case "*":
                result = Integer.toString(operand1 * operand2);
                break;
            case "/":
                result = Integer.toString(operand1 / operand2);
                break;
            default:
                throw new Exception("Введена неверная операция. Пожалуйста, используйте только +,-,*,/");
        }
        if (isRoman) {
            if (isArabic) throw  new Exception("Используйте только арабские или только римские числа");
            if (Integer.parseInt(result) < 1) throw new Exception("Результат вычисления меньше 1");
            result = intToRoman(Integer.parseInt(result));

        }
        return result;
    }
    enum Roman {
        I(1), II ( 2), III (3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
        private final int value;
        private Roman(int value) {
            this.value = value;
        }
        public int toInt() {
            return value;
        }
    }
    static String intToRoman (int number) {
        String keys [] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int values [] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < keys.length) {
            while (number >= values[index])  {
                int d = number / values[index];
                number = number % values[index];
                for (int i = 0; i < d; i++) {
                    result.append(keys[index]);
                }
            }
            index++;
        }
        return result.toString();
    }
}