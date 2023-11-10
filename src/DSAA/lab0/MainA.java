package DSAA.lab0;

import java.util.Scanner;

public class MainA {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        for (int i = 0; i < number; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            int width = 2 * (a + b) + 1;
            int length = 2 * (b + c) + 1;
            String[][] arr = new String[length][width];
            //全填点
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < width; k++) {
                    arr[j][k] = ".";
                }
            }
            //正面
            for (int j = 2 * b; j < length; j += 2) {
                for (int k = 0; k <= 2 * a; k++) {
                    if (k % 2 == 0){
                        arr[j][k] = "+";
                    }else {
                        arr[j][k] = "-";
                    }
                    if (k == 2 * a){
                        for (int l = 1; l <= 2 * b; l++) {
                            if (l % 2 == 0){
                                arr[j - l][k + l] = "+";
                            }else {
                                arr[j - l][k + l] = "/";
                            }
                        }
                    }
                }
            }
            for (int j = 2 * b + 1; j < length; j += 2) {
                for (int k = 0; k <= 2 * a; k++) {
                    if (k % 2 == 0){
                        arr[j][k] = "|";
                    }
                }
            }
            //顶
            for (int j = 0; j < 2 * b; j += 2) {
                for (int k = 2 * b - j; k < width - j; k++) {
                    if (k % 2 == 0){
                        arr[j][k] = "+";
                    }else {
                        arr[j][k] = "-";
                    }
                    if (k == width - j - 1){
                        for (int l = 0; l <= 2 * c; l++) {
                            if (l % 2 == 1){
                                arr[j + l][k] = "|";
                            }
                        }
                    }
                }
                
            }
            for (int j = 1; j < 2 * b; j += 2) {
                for (int k = 2 * b - j; k < width - j; k++) {
                    if (k % 2 == 1){
                        arr[j][k] = "/";
                    }
                }
            }
            //侧
            /*for (int j = 2; j <= 2 * c; j += 2) {
                for (int k = width - 1; k > 2 * a ; k -= 2 ) {
                    arr[][] = "/";
                }
            }*/

            for (int j = 0; j < length; j++) {
                for (int k = 0; k < width; k++) {
                    System.out.print(arr[j][k]);
                }
                System.out.println();
            }
        }
    }
}
/*
..+-+
././|
+-+.+
|.|/.
+-+..
....+-+-+-+-+-+-+
.../././././././|
..+-+-+-+-+-+-+.+
./././././././|/|
+-+-+-+-+-+-+.+.+
|.|.|.|.|.|.|/|/|
+-+-+-+-+-+-+.+.+
|.|.|.|.|.|.|/|/|
+-+-+-+-+-+-+.+.+
|.|.|.|.|.|.|/|/.
+-+-+-+-+-+-+.+..
|.|.|.|.|.|.|/...
+-+-+-+-+-+-+....
3
1 1 1
6 2 4
1 1 1
 */