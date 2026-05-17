/*
Задание 4
Дан массив целых чисел nums.
Напишите программу, выводящую минимальное количество ходов, требуемых для приведения всех элементов массива к одному числу.
За один ход можно уменьшить или увеличить число массива на 1.
Имеется ограничение по максимальному количеству ходов – 20.
Необходимо вывести минимальное количество ходов. В случае, если за 20 ходов это сделать невозможно, необходимо вывести соответствующее сообщение.
Элементы массива читаются из файла, переданного в качестве аргумента командной строки!
Логика:
nums = [4, 5, 6]
Решение: [4, 5, 6] => [5, 5, 6] => [5, 5, 5].
Минимальное количество ходов: 2.

Пример 1:
На вход подаётся файл с содержимым:
3
6
8
9
Вывод в консоль: 8

Пример 2:
На вход подаётся файл с содержимым:
1
16
3
20
Вывод в консоль: «20 ходов недостаточно для приведения всех элементов массива к одному числу».
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Task4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Нужен один аргумент");
            return;
        }
        List<Integer> numbers = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(args[0]))) {
            while (fileScanner.hasNextInt()) {
                numbers.add(fileScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден" + e.getMessage());
            return;
        }
        if (numbers.isEmpty()) {
            System.out.println("Файл пуст");
            return;
        }
        Collections.sort(numbers);
        int mediana = numbers.get(numbers.size() / 2);
        long moves = 0;
        for (int n : numbers) {
            moves += Math.abs(n - mediana);
        }
        if (moves > 20) {
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        } else {
            System.out.println(moves);
        }
    }
}
