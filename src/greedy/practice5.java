package greedy;



import java.util.Scanner;

public class practice5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        interval[] intervals = new interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new interval(i);
            intervals[i].start = input.nextInt();
            intervals[i].end = input.nextInt();
        }
        quickSortData(intervals, 0, intervals.length - 1);
        int ans = 1;
        int intervalCounter = 0;
        int intervalIndex = 0;
        int curPoint = 0;
        curPoint = intervals[intervalIndex].end;
        while (intervalCounter != n){
            intervalIndex++;
            if (intervalIndex == n){
                break;
            }
            if (intervals[intervalIndex].start <= curPoint){
                intervalCounter++;
            }else {
                curPoint = intervals[intervalIndex].end;
                intervalCounter++;
                ans++;
            }
        }
        System.out.println(ans);

    }
    public static void quickSortData(interval[] intervals, int left, int right){
        if (left >= right){
            return;
        }
        int leftt = left;
        int rightt = right;
        interval basic = intervals[leftt];
        while (leftt < rightt){
            while (leftt < rightt && intervals[rightt].end > basic.end){///
                rightt--;
            }
            if (leftt < rightt){
                intervals[leftt] = intervals[rightt];
            }
            while (leftt < rightt && intervals[leftt].end <= basic.end){///
                leftt++;
            }
            if (leftt < rightt){
                intervals[rightt] = intervals[leftt];
            }
            if (leftt >= rightt){
                intervals[leftt] = basic;
            }
        }
        quickSortData(intervals, left, rightt - 1);
        quickSortData(intervals, rightt + 1, right);
    }
}
class interval{
    int no;
    int start;
    int end;
    public interval(int no){
        this.no = no;
    }
}
/*
6
1 3
2 5
4 6
4 7
5 8
3 6
 */