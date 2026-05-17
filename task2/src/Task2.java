/*
Задание 2
Напишите программу, которая рассчитывает положение точек относительно эллипса.
Координаты центра эллипса и его радиусы считываются из файла 1.
Пример:
0 0 – координаты центра
5 3 – координаты радиуса

Координаты точек считываются из файла 2.
Пример:
0 3
0 0
6 0

Вывод для данных примеров файлов:
0
1
2
Пути к файлам передаются программе в качестве аргументов!
● файл с координатами и радиусом эллипса - 1 аргумент;
● файл с координатами точек - 2 аргумент;
● координаты - рациональные числа в диапазоне от 10-38 до 1038;
● количество точек от 1 до 100;
● вывод каждого положения точки заканчивается символом новой строки;
● соответствия ответов:
○ 0 - точка лежит на окружности
○ 1 - точка внутри
○ 2 - точка снаружи.
Вывод программы в консоль!
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Task2 {

    static class Point {
        double x,y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length < 2) {
            System.out.println("Нужны два аргумента");
            return;
        }
        String file_name1 = args[0];

        File file1 = new File(file_name1);
        if(!file1.exists()) {
            System.out.println("Файл не найден");
            return;
        }
        Scanner fileScanner = new Scanner(file1);
        double cx = fileScanner.nextDouble();
        double cy = fileScanner.nextDouble();
        double a = fileScanner.nextDouble();
        double b = fileScanner.nextDouble();
        fileScanner.close();

        String file_name2 = args[1];
        File file2 = new File(file_name2);
        if(!file2.exists()) {
            System.out.println("Файл не найден");
            return;
        }
        Scanner fileScanner2 = new Scanner(file2);
        List<Point> pointsDots = new ArrayList<>();
        while(fileScanner2.hasNextDouble()){
            double x = fileScanner2.nextDouble();
            double y = fileScanner2.nextDouble();
            pointsDots.add(new Point(x,y));
        }
        fileScanner2.close();

        for(Point point : pointsDots) {
            double currentX = point.x;
            double currentY = point.y;
            double formula = pow(((currentX - cx) / a), 2) + pow(((currentY - cy) / b), 2);
            if( (Math.abs(formula - 1) < 1e-9) ) {
                System.out.println("0");
            } else if ((formula < 1)) {
                System.out.println("1");
            } else System.out.println("2");
        }

    }
}
