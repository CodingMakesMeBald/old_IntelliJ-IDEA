package DSAA.lab2;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        for (int i = 0; i < testCases; i++) {
            int length = input.nextInt();
            if (length == 1){
                System.out.println(0);
                continue;
            }
            int[] array = new int[length];
            for (int j = 0; j < length; j++) {
                array[j] = input.nextInt();
            }
            int[] temp = array.clone();
            System.out.println(inverseNumber(array, temp, 0, array.length - 1));

        }
    }

    public static long inverseNumber(int[] array, int[] temp, int left, int right){//归并求逆序数
        if (left == right){
            return 0;
        }
        long counter = 0L;
        int mid = (left + right) / 2;
        long leftHalfInverseNumber = inverseNumber(temp, array, left, mid);
        long rightHalfInverseNumber = inverseNumber(temp, array, mid + 1, right);
        int l = mid;
        int r = right;
        int tempIndex = right;
        while (l >= left && r >= mid + 1){
            if (array[l] > array[r] ){//左半最右边数大于右半最大的数
                temp[tempIndex--] = array[l--];
                counter += (r - mid);
            }else {
                temp[tempIndex--] = array[r--];
            }
        }
        for (l = l; l >= left ; l--) {
            temp[tempIndex--] = array[l];
        }
        for (r = r; r >= mid + 1 ; r--) {
            temp[tempIndex--] = array[r];
        }
        counter += leftHalfInverseNumber;
        counter += rightHalfInverseNumber;
        return counter;
    }
}
