package DSAA.lab0;

import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String dealer = input.next();
        String Paul1 = input.next();
        String Paul2 = input.next();
        String Paul3 = input.next();
        String Paul4 = input.next();
        String Paul5 = input.next();
        if (dealer.charAt(0) == Paul1.charAt(0) || dealer.charAt(0) == Paul2.charAt(0) || dealer.charAt(0) == Paul3.charAt(0) ||
                dealer.charAt(0) == Paul4.charAt(0) || dealer.charAt(0) == Paul5.charAt(0) || dealer.charAt(1) == Paul1.charAt(1) ||
                dealer.charAt(1) == Paul2.charAt(1) || dealer.charAt(1) == Paul3.charAt(1) || dealer.charAt(1) == Paul4.charAt(1) ||
                dealer.charAt(1) == Paul5.charAt(1)  ){
            System.out.println("All in");
        }else {
            System.out.println("Fold");
        }
    }
}
