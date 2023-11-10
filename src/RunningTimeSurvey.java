import java.util.Arrays;
import java.util.Comparator;

public class RunningTimeSurvey {
    static class Task {
        int time;  // 执行任务所需的时间
        int ddl;  // 任务截止时间
        int penalty;  // 误时惩罚

        Task(int time, int ddl, int penalty) {
            this.time = time;
            this.ddl = ddl;
            this.penalty = penalty;
        }
    }
    public static int minimizePenalty(Task[] tasks) {
        int n = tasks.length;
        int maxddl = 0;
        for (Task task : tasks) {
            if (task.ddl > maxddl) {
                maxddl = task.ddl;
            }
        }
        int[][] dp = new int[n + 1][maxddl + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        dp[0][0] = 0;
        // 按截止时间排序
        Arrays.sort(tasks, Comparator.comparingInt(task -> task.ddl));
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= maxddl; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + tasks[i - 1].penalty);// 做task[i]
                if (j >= tasks[i - 1].time && j <= tasks[i - 1].ddl) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - tasks[i - 1].time]);// 不做task[i]
                }
            }
        }
        int minPenalty = Integer.MAX_VALUE;
        for (int penalty : dp[n]) {
            if (penalty < minPenalty) {
                minPenalty = penalty;
            }
        }
        return minPenalty;
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[] {
                new Task(2, 4, 70),
                new Task(2, 2, 60),
                new Task(1, 4, 50),
                new Task(2, 3, 40),
                new Task(1, 4, 40),
                new Task(2, 6, 10),
        };

        int minPenalty = minimizePenalty(tasks);
        System.out.println("Minimum penalty: " + minPenalty);
    }
}

