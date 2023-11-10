package DSAA.lab4;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack stack = new Stack();
        String in = input.next();
        for (int i = 0; i < in.length(); i++) {
            if (stack.data[stack.top] >= 514329){
                stack.data[stack.top] %= 514329;
            }
            if (in.charAt(i) == '('){
                stack.push(0);
            } else if (in.charAt(i) == ')') {
                if(stack.data[stack.top] == 0){
                    stack.pop();
                    stack.data[stack.top] += 1;
                }else{
                    int temp = stack.pop();
                    temp *= 2;
                    stack.data[stack.top] += temp;
                }
            }
        }
        System.out.println(stack.data[stack.top]%514329);
    }
}

class Stack{
    public int[] data = new int[100000];
    public int top = 0;

    public void push(int a){
        top++;
        data[top] = a;
    }

    public int pop(){
        int temp = data[top];
        if (top > 0){
            top--;
        }
        return temp;
    }
}
