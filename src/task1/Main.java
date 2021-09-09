package task1;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        String pathFile = args[0];                                                    //"src/task1/input.txt"
        int[] array;

        try (BufferedReader in = new BufferedReader(new FileReader(pathFile))) {
            array = in.lines().mapToInt(Integer::parseInt).toArray();

            if (array != null ) {
                System.out.printf("%.2f%n", Percentile(array, 90));;          //90 перцентиль
                System.out.printf("%.2f%n", Percentile(array, 50));           //медиана
                System.out.printf("%.2f%n", Percentile(array, 100));          //максимальное значение
                System.out.printf("%.2f%n", Percentile(array, 0));            //минимальное значение
                System.out.printf("%.2f%n",argValue(array));                          //среднее значение

            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (NumberFormatException e){
            System.out.println("Некорректный входной файл");
        }
    }

    public static double Percentile(int [] array, double percNum) {
        Arrays.sort(array);
        if (percNum < 0 || percNum>100) {
            System.out.println("Значение процентилей выходит за границы");

        }

        if (array.length == 1) {
            return array[0];
        }

        double 	interval = 1.0 / (array.length - 1);

        int leftBoundInd = (int) ((percNum / 100.0) / interval);

        int rightBoundInd;
        if (leftBoundInd == array.length - 1)
            rightBoundInd = leftBoundInd;
        else
            rightBoundInd = leftBoundInd + 1;

        double percentile = ((((percNum / 100.0) - interval*leftBoundInd) / interval))*(array[rightBoundInd] - array[leftBoundInd]) + array[leftBoundInd];
        return percentile;
    }

    // Среднее арифметическое
    public static double argValue(int[] array) {
        double arg = 0;
        for (int i = 0; i < array.length; i++) {
            arg += array[i];
        }
        arg /= array.length;
        return arg;
    }
}

