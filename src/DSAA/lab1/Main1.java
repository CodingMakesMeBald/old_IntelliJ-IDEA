package DSAA.lab1;
import java.util.Scanner;
public class Main1 {
    public static boolean up = false;
    public static boolean low = false;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int number = input.nextInt();
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = input.nextInt();
        }
        for (int i = 0; i < number; i++) {
            int counter = 0;
            up = false;
            low = false;//上下界在数组中能否找到
            int lowerBound = input.nextInt();
            int upperBound = input.nextInt();
            int lowerIndex = 0;
            int upperIndex = 0;
            if (upperBound - lowerBound <= 1){//上下界为连续整数
                System.out.println("NO");
                continue;
            }else if (lowerBound >= array[length - 1]){//下界不小于比数组中最大的数
                System.out.println("NO");
                continue;
            }else if (upperBound <= array[0]){//上界不大于数组中最小的数
                System.out.println("NO");
                continue;
            }else if (lowerBound < array[0] && upperBound > array[length - 1]){//上下界把整个数组包含
                System.out.println("YES " + length);
                continue;
            }else if (lowerBound < array[0]){//下界在数组外，上界在数组内
                upperIndex = binarySearchUp(array, 0, array.length - 1, upperBound);
                if (upperBound != 0){
                    System.out.println("YES " + upperIndex);
                }else {
                    System.out.println("NO");
                }
            } else if (upperBound > array[length - 1]) {//下界在数组内，上界在数组外
                lowerIndex = binarySearchLow(array, 0, array.length - 1, lowerBound);
                if (lowerIndex != (array.length - 1)){
                    if (low){
                        System.out.println("YES " + (length - lowerIndex - 1));
                    }else {
                        System.out.println("YES " + (length - lowerIndex));
                    }
                }else {
                    System.out.println("NO");
                }
            }else {//上下界都在数组内
                lowerIndex = binarySearchLow(array, 0, array.length - 1, lowerBound);
                upperIndex = binarySearchUp(array, 0, array.length - 1, upperBound);
                if (low){
                    counter += (upperIndex - lowerIndex - 1);
                }else {
                    counter += (upperIndex - lowerIndex);
                }
                if (counter > 0){
                    System.out.println("YES " + counter);
                }else {
                    System.out.println("NO");
                }
            }


        }
    }
    public static int binarySearchLow(int[] arr, int left, int right, int target){
        while (left <= right){
            int mid = (left + right) / 2;
            if (target > arr[mid]){
                left = mid + 1;
                return binarySearchLow(arr, left, right, target);
            } else if (target < arr[mid]){
                right = mid - 1;
                return binarySearchLow(arr, left, right, target);
            } else {
                while ( mid + 1 <= arr.length - 1 && target == arr[mid + 1]){
                    mid++;
                }
                low = true;
                return mid;
            }
        }
        return left;
    }
    public static int binarySearchUp(int[] arr, int left, int right, int target){
        while (left <= right){
            int mid = (left + right) / 2;
            if (target > arr[mid]){
                left = mid + 1;
                return binarySearchUp(arr, left, right, target);
            } else if (target < arr[mid]){
                right = mid - 1;
                return binarySearchUp(arr, left, right, target);
            } else  {
                while ( mid - 1 >= 0 && target == arr[mid - 1] ){
                    mid--;
                }
                up = true;
                return mid;
            }
        }
        return left;
    }

}

