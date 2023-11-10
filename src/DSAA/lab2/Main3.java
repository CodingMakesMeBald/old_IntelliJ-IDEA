package DSAA.lab2;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }
        int[] temp = new int[n];
        mergeSort(array, 0, array.length - 1, temp);
        int kIndex = (int) Math.floor((double)n / 3);
        int k = array[kIndex];
        System.out.println(k);
        System.out.print(array[0] + " ");
        int i = 1;
        int counter = 0;
        for (int j = kIndex; j < n; j++) {
            if (counter == 2 && i < kIndex){
                System.out.print(array[i] + " ");
                i++;
                counter = 0;
            }
            System.out.print(array[j] + " ");
            counter++;
        }


    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);

            int i = left;
            int j = mid + 1;
            int t = 0;

            while (i <= mid && j <= right){
                if (arr[i] <= arr[j]){
                    temp[t] = arr[i];
                    t++;
                    i++;
                }else {
                    temp[t] = arr[j];
                    t++;
                    j++;
                }
            }

            while (i <= mid){
                temp[t] = arr[i];
                t++;
                i++;
            }
            while (j <= right){
                temp[t] = arr[j];
                t++;
                j++;
            }

            t = 0;
            int l = left;
            while (l <= right){
                arr[l] = temp[t];
                t++;
                l++;
            }
        }
    }
}
