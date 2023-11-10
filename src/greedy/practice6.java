package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class practice6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = input.nextInt();//总共n堆石子
            int k = input.nextInt();//一次最多合并k堆
            PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            int sum = 0;
            int[] stones = new int[n];
            for (int j = 0; j < n; j++) {
                stones[j] = input.nextInt();
                bigHeap.add(stones[j]);
                sum += stones[j];
            }
            //max
            int max = 0;
            while (bigHeap.size() > 1){
                int temp1 = bigHeap.poll();
                int temp2 = bigHeap.poll();
                int temp = temp1 + temp2;
                max += temp;
                bigHeap.add(temp);
            }
            //min
            int min = 0;
            if (k >= n){
                min = sum;
            }else {
                Arrays.sort(stones);
                int remain = n % (k - 1);
                int quotient = n / (k - 1);
                int initial = 0;
                int index = 0;

                for (; index < remain; index++) {
                    initial += stones[index];
                }
                if (remain != 1){
                    min += initial;
                }
                for (int j = 0; j < quotient; j++) {
                    int temp = index + k - 2;
                    for (; index <= temp; index++) {
                        initial += stones[index];
                    }
                    min += initial;
                }

            }
            System.out.println("max: " + max);
            System.out.println("min: " + min);

        }

    }
}
/*
3
3 4
1 2 3
5 3
4 5 6 3 2
6 4
4 5 6 3 2 10

1
5 3
4 5 6 3 2
 */
