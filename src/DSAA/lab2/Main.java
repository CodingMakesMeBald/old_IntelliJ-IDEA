package DSAA.lab2;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//n个数
        int m = input.nextInt();//最大交换次数
        int k = input.nextInt();//交换一次的代价
        int[] array = new int[n];//初始数组
        long result = 0;
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
            result += array[i];
        }
        int[] get = new int[1800000];//存每次交换收益的数组
        int getIndex = 0;//上面get数组的指针
        for (int i = 0; i < n; i++) {//遍历数组中的数
            if (array[i] >= 0){
                int a = array[i];
                String b = String.valueOf(a);
                char[] arr = b.toCharArray();
                for (int j = 0; j < arr.length; j++) {//遍历数组中每个数的数字
                    char temp;
                    int maxIndex = j;
                    for (int l = j; l < arr.length; l++) {

                        if (arr[maxIndex] < arr[l]){
                            maxIndex = l;
                        }
                    }
                    if (arr[j] != arr[maxIndex]){
                        temp = arr[j];
                        arr[j] = arr[maxIndex];
                        arr[maxIndex] = temp;
                        String c = String.valueOf(arr);
                        int d = Integer.parseInt(c);
                        get[getIndex] += (d - a);
                        getIndex++;
                        a = d;
                    }else {
                        continue;
                    }

                }
            }else {//小于等于0
                int a = array[i];
                String b = String.valueOf(a).replace("-","");

                char[] arr = b.toCharArray();
                for (int j = 0; j < arr.length; j++) {//遍历数组中每个数的数字
                    char temp;
                    int minIndex = j;
                    for (int l = j; l < arr.length; l++) {

                        if (arr[minIndex] > arr[l]){
                            minIndex = l;
                        }
                    }
                    if (arr[j] != arr[minIndex]){
                        temp = arr[j];
                        arr[j] = arr[minIndex];
                        arr[minIndex] = temp;
                        String c = "-" + String.valueOf(arr);
                        int d = Integer.parseInt(c);
                        get[getIndex] += (d - a);
                        getIndex++;
                        a = d;
                    }else {
                        continue;
                    }

                }
            }

        }
        int[] gett = get.clone();
        mergeSort(get, 0, get.length - 1, gett);


        for (int i = 0; i < get.length; i++) {
            if (get[i] > k && i  < m){
                result += (get[i] - k);
            }else {
                break;
            }
        }
        System.out.println(result);
        
        

    }
    static void mergeSort(int[] arr, int left, int right, int[] temp){//归并排序
        if (left < right){
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);

            int i = left;
            int j = mid + 1;
            int t = 0;

            while (i <= mid && j <= right){
                if (arr[i] >= arr[j]){
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
