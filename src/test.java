import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class test {
    static ArrayList<Integer> array = new ArrayList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task();
        }
        for (int i = 0; i < n; i++) {
            tasks[i].id = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            tasks[i].deadline = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            tasks[i].penalty = input.nextInt();
        }
        System.out.println(scheduleTasks(tasks));
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");
        }
    }

    public static int scheduleTasks(Task[] tasks) {
        //按照惩罚值降序排列
        Arrays.sort(tasks, Comparator.comparing(Task::getPenalty).reversed());

        int totalPenalty = 0;// 初始化总惩罚值为0
        boolean[] slots = new boolean[tasks.length]; // 用于标记某个时刻是否有任务占用

        for (int i = 0; i < tasks.length; i++) {

            int slot = Math.min(tasks[i].deadline - 1, tasks.length - 1);// 找到任务截止时间和任务数量的较小值
            while (slot >= 0 && slots[slot]) {// 从该时刻开始往前找，找到第一个空闲的时刻
                slot--;
            }


            if (slot >= 0) {// 如果找到了空闲时刻，则将任务安排在该时刻
                slots[slot] = true;

            } else {// 如果没有找到空闲时刻，则说明任务已经过期，需要增加惩罚值
                totalPenalty += tasks[i].penalty;

            }
        }

        return totalPenalty;
    }
}

class Task {
    int id;
    int deadline;
    int penalty;

    public Task() {
    }

    public Task(int id, int deadline, int penalty) {
        this.id = id;
        this.deadline = deadline;
        this.penalty = penalty;
    }

    public int getPenalty() {
        return penalty;
    }
}
/*
7
1 2 3 4 5 6 7
4 2 4 3 1 4 6
70 60 50 40 30 20 10
 */
/*
1.定义一个二维数组dp，其中dp[i][j]表示在前i个任务中，使得任务在时间不超过j的情况下的最小罚款总和。初始化dp数组为罚款的最大值+1。
2.对于每个任务𝑎𝑖，我们需要考虑两种情况：
    如果任务𝑎𝑖在截止时间𝑑𝑖之前完成，那么当前罚款总和为dp[i-1][j-𝑡𝑖]。
    如果任务𝑎𝑖没有在截止时间𝑑𝑖之前完成，那么当前罚款总和为dp[i-1][j] + 𝑤𝑖。
我们选择上述两种情况中的较小值作为dp[i][j]的值。
3.最后，遍历整个dp数组的最后一行，找到最小的罚款总和。
 */
