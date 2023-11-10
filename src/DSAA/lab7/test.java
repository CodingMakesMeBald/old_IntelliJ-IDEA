package DSAA.lab7;

public class test {
    public static void main(String[] args) {

        System.out.println(power(2,2));
    }

    static double power(double a, double n){
        if (n == 1){
            return Math.pow(a, 3);
        }else {
            return Math.pow(power(a, n -1), 3);
        }
    }
}
