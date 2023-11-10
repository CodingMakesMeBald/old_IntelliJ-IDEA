package TopologicalGreed;

import java.util.*;

public class practice1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // 顶点数
        int m = input.nextInt(); // 边数
        int[] indegrees = new int[n+1]; // 记录点的入度
        List<Integer>[] graph = new List[n+1]; // 存储图
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            indegrees[b]++;
            graph[a].add(b);
        }
        input.close();

        // 进行拓扑排序
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) { // 入度为0的点先加入队列
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append(curr).append(" ");
            for (int neighbor : graph[curr]) {
                indegrees[neighbor]--; // 把依赖于 curr 的任务的入度减1
                if (indegrees[neighbor] == 0) { // 如果它们的入度都变成0了，则加入队列
                    queue.offer(neighbor);
                }
            }
        }

        // 判断是否存在环（即有任务的入度始终不为0）
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] != 0) {
                System.out.println("impossible");
                return;
            }
        }

        // 输出结果
        System.out.println(sb.toString());
    }
}
