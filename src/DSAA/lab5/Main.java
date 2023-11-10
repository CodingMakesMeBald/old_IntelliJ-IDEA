package DSAA.lab5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String a = input.next();
        String b = input.next();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == 'N'){
                stringBuilder.append('S');
            }else if (a.charAt(i) == 'S'){
                stringBuilder.append('N');
            }else if (a.charAt(i) == 'E'){
                stringBuilder.append('W');
            }else if (a.charAt(i) == 'W'){
                stringBuilder.append('E');
            }
        }
        String c = stringBuilder.reverse().toString();
        String d = c.concat(b);

        int[] next = new int[d.length()];
        next[0] = 0;
        int k = 0;
        for(int i = 1; i < d.length(); i++){
            while(k > 0 && d.charAt(k) != d.charAt(i)){
                k = next[k - 1];
            }
            if(d.charAt(i) == d.charAt(k)){
                k++;
            }
            next[i] = k;
        }

        if (next[next.length - 1] == 0){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }

    }
}
