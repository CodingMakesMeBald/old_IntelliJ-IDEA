package DSAA.lab1;

import java.util.Scanner;

public class Main3 {
    public static double mid;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int studentNumber = input.nextInt();
        int mooncakeNumber = input.nextInt();
        double sum = 0;
        int[] mooncake = new int[mooncakeNumber];

        for (int i = 0; i < mooncakeNumber; i++) {
            mooncake[i] = input.nextInt();

            sum += mooncake[i] * mooncake[i] * Math.PI;
        }
        double maxInTheory = sum  / studentNumber;

        double left = 0;
        double right = maxInTheory;

        //double precision = 0;
        while (right >= left + 0.000000001){

            mid = (left + right) / 2;
            int counter = 0;
            for (int i = 0; i < mooncake.length; i++) {
                counter += Math.floor(mooncake[i] * mooncake[i] * Math.PI / mid);
            }
            if (counter >= studentNumber ){
                left = mid;
            }else {
                right = mid;
            }
        }
        System.out.printf("%.14s",mid );

    }


}
