package DSAA.lab1;

import java.util.Scanner;

public class Main2 {
    public static long counter;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        if (length < 3){
            System.out.println(0);
            return;
        }
        int sum = input.nextInt();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = input.nextInt();
        }
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                binarySearch(arr, j + 1, length - 1, sum - arr[i] - arr[j]);
            }
        }
        System.out.println(counter);
    }
    public static boolean binarySearch(int[] arr, int left, int right, int target){
        while (left <= right){
            int mid = (left + right) / 2;
            if (target > arr[mid]){
                left = mid + 1;
                return binarySearch(arr, left, right, target);
            } else if (target < arr[mid]){
                right = mid - 1;
                return binarySearch(arr, left, right, target);
            } else {
                counter++;
                int midd = mid;
                while (mid - 1 >= left && arr[mid - 1] == target){
                    counter++;
                    mid--;
                }
                mid = midd;
                while (mid + 1 <= right && arr[mid + 1] == target){
                    counter++;
                    mid++;
                }
                return true;
            }
        }
        return false;
    }
}
