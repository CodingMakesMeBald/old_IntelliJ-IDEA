package DSAA.lab8;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        Node2[] graph = new Node2[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node2(i + 1);
        }
        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            graph[v - 1].child.add(graph[u - 1]);
        }

        Node2[] q = new Node2[n];
        for (int i = 0; i < n; i++) {
            int front = 0;
            int rear = 0;
            if (graph[graph.length - 1 - i].isVisited){
                continue;
            }
            q[rear++] = graph[graph.length - 1 - i];
            graph[graph.length - 1 - i].isVisited = true;
            graph[graph.length - 1 - i].ans = graph[graph.length - 1 - i].number;
            while (front != rear){
                Node2 head = q[front ++];
                for (int j = 0; j < head.child.size(); j++) {
                    if (!head.child.get(j).isVisited){
                        q[rear++] = head.child.get(j);
                        head.child.get(j).ans = head.ans;
                        head.child.get(j).isVisited = true;
                    }
                }
            }
        }

        for (int i = 0; i < graph.length; i++) {
            System.out.print(graph[i].ans + " ");
        }

    }
}

class Node2{
    int number;
    ArrayList<Node2> child = new ArrayList<>();
    boolean isVisited;
    int ans;

    public Node2(int number){
        this.number = number;
    }

}