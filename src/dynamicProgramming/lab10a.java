package dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class lab10a {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] w = new int[n + 1];//beauty
        for (int i = 1; i <= n; i++) {
            w[i] = input.nextInt();
        }
        int[] c = new int[n + 1];//cost
        for (int i = 1; i <= n; i++) {
            c[i] = input.nextInt();
        }
        int[] M = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= c[i]; j--) {
                M[j] = Math.max(M[j], w[i] + M[j - c[i]]);
            }
        }
        System.out.println(M[m]);
    }
}
/*
5 11
1 6 18 22 28
1 2 5 6 7
 */