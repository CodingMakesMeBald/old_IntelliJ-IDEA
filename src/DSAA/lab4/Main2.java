package DSAA.lab4;


import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        int[] a = new int[100000];
        ArrayList<Integer> arrayList = new ArrayList<>();
        int n = input.nextInt();
        int front = -1;
        int rear = -1;
        for (int i = 0; i < n; i++) {
            String aa = input.next();
            if (aa.equals("E")){
                front++;
                int ll = input.nextInt();
                a[front] = ll;
            }else
            if (aa.equals("D")){
                int f = input.nextInt();
                for (int j = 0; j < f; j++) {
                    rear++;
                    a[rear] = 0;
                }
                arrayList.add(a[rear + 1]);
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        input.close();
    }
}
