package DSAA.lab8;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int q = input.nextInt();
        int[][] array = new int[n][n];
        for (int i = 0; i < m; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            array[a - 1][b - 1] = 1;
            array[b - 1][a - 1] = 1;
        }
        for (int i = 0; i < q; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            if (array[a - 1][b - 1] == 1){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }
        }
    }
}
