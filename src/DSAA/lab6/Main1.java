package DSAA.lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int num = input.nextInt();
        node1[] tree = new node1[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node1();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            int w = input.nextInt();
            tree[u].child.add(tree[v]);
            tree[u].w.add(w);
            tree[v].child.add(tree[u]);
            tree[v].w.add(w);
        }
        //bfs
        node1[] q = new node1[n];
        int front = 0;
        int rear = 0;
        q[rear++] = tree[0];
        tree[0].isVisited = true;
        tree[0].path = 0;
        int cnt = 0;

        while (front != rear){
            node1 head = q[front ++];
            if (head.path == num && head.child.size() == 1){
                cnt++;
            }
            for (int i = 0; i < head.child.size(); i++) {
                if (!head.child.get(i).isVisited){
                    q[rear++] = head.child.get(i);
                    head.child.get(i).path = head.path + head.w.get(i);
                    head.child.get(i).isVisited = true;
                }
            }
        }
        System.out.println(cnt);
    }

    static void dfs(node1 a){
        a.isVisited = true;
        for (int i = 0; i < a.child.size(); i++) {
            if (!a.child.get(i).isVisited){
                a.child.get(i).path = a.path + a.w.get(i);
                dfs(a.child.get(i));
            }
        }
    }
}

class node1{
    boolean isVisited;
    int path;
    ArrayList<node1> child = new ArrayList<>();
    ArrayList<Integer> w = new ArrayList<>();
}
