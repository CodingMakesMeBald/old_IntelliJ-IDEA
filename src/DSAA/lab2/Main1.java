package DSAA.lab2;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = input.nextInt();
        }
        quickSort(array, 0, array.length - 1);
        long sum = 0;
        for (int i = 0; i < number / 2; i++) {
            sum += (long) array[i] * array[number - 1 - i];
        }
        System.out.println(sum);
    }

    public static void quickSort(int[] array, int left, int right){
        if (left >= right){
            return;
        }
        int leftt = left;
        int rightt = right;
        int basic = array[leftt];
        while (leftt < rightt){
            while (leftt < rightt && array[rightt] > basic){
                rightt--;
            }
            if (leftt < rightt){
                array[leftt] = array[rightt];
            }
            while (leftt < rightt && array[leftt] <= basic){
                leftt++;
            }
            if (leftt < rightt){
                array[rightt] = array[leftt];
            }
            if (leftt >= rightt){
                array[leftt] = basic;
            }
        }
        quickSort(array, left, rightt - 1);
        quickSort(array, rightt + 1, right);
    }
}
