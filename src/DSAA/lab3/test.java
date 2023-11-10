package DSAA.lab3;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long money = input.nextLong();
        long priceCounter = input.nextLong();
        long q = (long) Math.floor((double)money / priceCounter );
        System.out.println(q);
    }
}
