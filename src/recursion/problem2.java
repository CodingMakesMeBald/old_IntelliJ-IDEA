package recursion;

import java.util.Scanner;

public class problem2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(cover(n));
    }
    public static int cover(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        return cover(n - 1) + cover(n - 2);
    }
}
