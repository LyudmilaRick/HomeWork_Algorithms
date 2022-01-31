package ru.skypro;

import ru.skypro.service.IntegerList;
import ru.skypro.service_impl.IntegerListImpl;
import ru.skypro.sort.SortUtils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // write your code here
        // Для получения массива случайных чисел
        int[] arr = generateRandomArray(100000);
        int[] copy1 = Arrays.copyOf(arr, arr.length);
        int[] copy2 = Arrays.copyOf(arr, arr.length);
        int[] copy3 = Arrays.copyOf(arr, arr.length);
        int[] copy4 = Arrays.copyOf(arr, arr.length);

        System.out.print("Пузырьковая сортировка - ");
        long start = System.currentTimeMillis();
        SortUtils.sortBubble(arr);
        System.out.print(System.currentTimeMillis() - start);

        System.out.print("\n");
        System.out.print("Сортировка выбором - ");
        start = System.currentTimeMillis();
        SortUtils.sortSelection(copy1);
        System.out.print(System.currentTimeMillis() - start);

        System.out.print("\n");
        System.out.print("Сортировка вставкой - ");
        start = System.currentTimeMillis();
        SortUtils.sortInsertion(copy2);
        System.out.print(System.currentTimeMillis() - start);

        System.out.print("\n");
        System.out.print("Быстрая сортировка - ");
        start = System.currentTimeMillis();
        SortUtils.quickSort(copy3, 0, 99999);
        System.out.print(System.currentTimeMillis() - start);

        System.out.print("\n");
        System.out.print("Сортировка слиянием - ");
        start = System.currentTimeMillis();
        SortUtils.mergeSort(copy4);
        System.out.print(System.currentTimeMillis() - start);

    }

    private static int[] generateRandomArray(int size) {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    private static IntegerList generateRandomIntegerArray(int size) {
        java.util.Random random = new java.util.Random();
        IntegerListImpl arr = new IntegerListImpl();
        for (int i = 0; i < size; i++) {
            Integer item = random.nextInt(100_000) + 100_000;
            arr.add(item);
        }
        return arr;
    }


}
