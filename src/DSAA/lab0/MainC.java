package DSAA.lab0;

import java.math.BigInteger;
import java.util.Scanner;

public class MainC {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int x2 = input.nextInt();
        int y2 = input.nextInt();
        int M = input.nextInt();
        int up = y2 - y1;
        int right = x2 - x1;
        int b = up + right;
        int a = 0;
        if (up < right){
            a = up;
        }else {
            a = right;
        }

        BigInteger u = factorial(BigInteger.valueOf(b));
        BigInteger d1 = factorial(BigInteger.valueOf(a));
        BigInteger d2 = factorial(BigInteger.valueOf(b - a));
        BigInteger d = d1.multiply(d2);
        BigInteger r = u.divide(d);
        BigInteger ans = r.mod(BigInteger.valueOf(M));
        System.out.println(ans.intValue());

    }
    public static BigInteger factorial(BigInteger bigInteger){
        BigInteger bigInteger1 = BigInteger.valueOf(1);
        for (int i = 1; i <= bigInteger.intValue() ; i++) {
            BigInteger bigInteger2 = BigInteger.valueOf(i);
            bigInteger1 = bigInteger2.multiply(bigInteger1);
        }

        return bigInteger1;
    }
}
