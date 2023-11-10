package BFS;



import java.util.*;

import java.util.*;

public class labQ1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] i = new int[n + 1];
        int[] ai = new int[n + 1];
        Node[] graph = new Node[n + 1];
        for (int j = 1; j <= n; j++) {
            graph[j] = new Node(input.nextInt());
        }
        for (int j = 1; j <= n; j++) {
            if (j - 1 >= 1){
                graph[j].neighbor.add(graph[j - 1]);
            }
            if (j + 1 <= n){
                graph[j].neighbor.add(graph[j + 1]);
            }
            graph[j].neighbor.add(graph[input.nextInt()]);
        }
        //bfs
        Node[] queue = new Node[n];
        int time = 0;
        int front = 0;
        int rear = 0;
        queue[rear] = graph[1];
        rear++;

        queue[0].minTime = time;
        while (front != rear) {
//            if (queue[front].isVisited){
//                front++;
//                continue;
//            }
            queue[0].isVisited = true;
            time++;
            for (int k = 0; k < queue[front].neighbor.size(); k++) {
                if (/*!queue[front].neighbor.get(k).isVisited &&*/ queue[front].neighbor.get(k).minTime > time){
                    queue[rear++] = queue[front].neighbor.get(k);
                    queue[front].neighbor.get(k).isVisited = true;
                    queue[front].neighbor.get(k).minTime = queue[front].minTime + 1;
                }
            }
            front++;
        }

        for (int j = 1; j <= n; j++) {
            System.out.print(graph[j].minTime + " ");
        }



    }
}

class Node{
    int number;
    int minTime = 100000000;
    ArrayList<Node> neighbor = new ArrayList<>();
    boolean isVisited;

    public Node(int number){
        this.number = number;
    }
}


/*
7
1 2 3 4 5 6 7
4 4 4 4 7 7 7

5
1 2 3 4 5
1 2 3 4 5

3
1 2 3
2 2 3
 */