package DSAA.lab8;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        Node3[] graph = new Node3[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node3(i + 1);
        }
        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            graph[u - 1].child.add(graph[v - 1]);
            graph[v - 1].child.add(graph[u - 1]);
        }
        Node3[] q = new Node3[n];
        for (int i = 0; i < n; i++) {
            int point = 0;
            int edge = 0;
            int front = 0;
            int rear = 0;
            if (graph[i].isVisited){
                continue;
            }
            q[rear++] = graph[i];
            graph[i].isVisited = true;
            while (front != rear){
                Node3 head = q[front ++];
                point++;
                edge += head.child.size();
                for (int j = 0; j < head.child.size(); j++) {
                    if (!head.child.get(j).isVisited){
                        q[rear++] = head.child.get(j);
                        head.child.get(j).isVisited = true;
                    }
                }
            }
            if (point <= edge / 2){
                System.out.println("Bad");
                return;
            }
        }
        System.out.println("Good");
    }
}

class Node3{
    int number;
    ArrayList<Node3> child = new ArrayList<>();
    boolean isVisited;
    

    public Node3(int number){
        this.number = number;
    }

}
