package merge;
import java.util.Scanner;
public class lab7a {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        long l = input.nextLong();
        long r = input.nextLong();
        System.out.println(sum(n, 1, r) - sum(n, 1, l - 1));
    }
    public static long sum(long n, long l, long r){
        int exponent = getBinaryLength(n);
        long length = (long)Math.pow(2, exponent) - 1;
        long mid = length / 2;
        if (r == mid){
            return n / 2;
        }else if (r == length){
            return n;
        }else if (r == mid + 1){
            return n / 2 + n % 2;
        } else if (r > mid + 1) {
            return n - sum(n, l, length - r);
        }else {
            return sum(n / 2, l, r);
        }
    }
    public static int getBinaryLength(long n){
        int i = 0;
        while (n != 0){
            i++;
            n /= 2;
        }
        return i;
    }

}
