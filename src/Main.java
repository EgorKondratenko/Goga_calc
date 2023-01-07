import java.util.Scanner;                             // импорт ввода с клавиатуры

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Input:");
        String ans = input.nextLine();

        String result = calc(ans);
        System.out.print("Result: ");
        System.out.println(result);

        input.close();
    }

    public static String calc(String input) {

        String[] ans = input.split(" ");         // разделяем строку на составляющие, разделитель пробел

        if (ans.length != 3) {                         // проверка длины строки, строка д.б. величиной три символа
            throw new RuntimeException("Ошибка! Введите корректное выражение");
        }

        String oper = ans[1];                          //обозначаем, что символ 1(т.е. второй в выражении) - операнд

        String[] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // массив римских значений
        int[] arabic = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};                                  // массив арабских цифр

        boolean isRomanA = false;                                                        // задаем флаг на поерделение, является ли А  римским
        boolean isRomanB = false;                                                        // задаем флаг на поерделение, является ли В  римским

        int indexA = 0;                                                                  // задаем индекс А
        int indexB = 0;                                                                  // задаем индекс В

        for (int i = 0; i < romans.length; i++) {                                        // проверяем, является ли число А на входе римским. если является, флаг А устанавливаем в "правду"
            if (romans[i].equals(ans[0])) {                                              // одновременно устанавливаем индекс входящего римского числа из массива romans
                isRomanA = true;
                indexA = i;
            }
        }

        for (int i = 0; i < romans.length; i++) {                                        //проверяем, является ли число В на входе римским. если является, флаг В устанавливаем в "правду"
            if (romans[i].equals(ans[2])) {                                              // одновременно устанавливаем индекс входящего римского числа из массива romans
                isRomanB = true;
                indexB = i;
            }
        }

        if ((isRomanA && !isRomanB) || (!isRomanA && isRomanB)) {                       // проверяем, если хотя бы одно число не римское, либо не входит в диапазон I-X, то выкидываем ошибку
            throw new RuntimeException("Ошибка! Числа в разных системах (римская/арабская) или превышено значение величин а или b для римских чисел.");
        }

        int result;

        int arabicA;
        int arabicB;

        if (isRomanA && isRomanB) {                                                      // если у нас на входе римские значения, то приводим соотвествие к арабским значениям

            arabicA = arabic[indexA];
            arabicB = arabic[indexB];

            result = calculate(oper, arabicA, arabicB);                                 // применяем метод для расчета

            if (result < 1) {
                throw new RuntimeException("Ошибка! Результат операции с римскими цифрами меньше 1.");
            }

            String otvetRoman = "";

            for (int i = 0; i < result; i++) {

                while (result == 100) {
                    otvetRoman += "C";
                    result -= 100;
                }

                while (result >= 90) {
                    otvetRoman += "XC";
                    result -= 90;
                }

                while (result >= 50) {
                    otvetRoman += "L";
                    result -= 50;
                }

                while (result >= 40) {
                    otvetRoman += "XL";
                    result -= 40;
                }

                while (result >= 10) {
                    otvetRoman += "X";
                    result -= 10;
                }

                while (result >= 5) {
                    otvetRoman += "V";
                    result -= 5;
                }

                while (result >= 4) {
                    otvetRoman += "IV";
                    result -= 4;
                }

                while (result >= 1) {
                    otvetRoman += "I";
                    result -= 1;
                }
            }

            return otvetRoman;
        }

        arabicA = Integer.parseInt(ans[0]);
        arabicB = Integer.parseInt(ans[2]);

        if ((arabicA < 1 || arabicA > 10) || (arabicB < 1 || arabicB > 10)) {
            throw new RuntimeException("Превышено значение величин а или b");                                       // Вводим ограничения на ввод значений
        }

        result = calculate(oper, arabicA, arabicB);

        return String.valueOf(result);
    }

    private static int calculate(String oper, int a, int b) {

        switch (oper) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new RuntimeException("Ошибка. Некорректный тип операции");
        }
    }
}
