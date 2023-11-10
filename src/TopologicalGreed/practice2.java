package TopologicalGreed;

import java.util.*;

class Interval {
    char name;
    int start;
    int end;

    public Interval(char name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

public class practice2 {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            String a = input.next();
            char b = a.charAt(0);
            intervals.add(new Interval(b, input.nextInt(), input.nextInt()));
        }

        List<Interval> selectedIntervals = intervalScheduling(intervals);

        StringBuilder sb = new StringBuilder();
        for (Interval interval : selectedIntervals) {
            sb.append(interval.name);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    public static List<Interval> intervalScheduling(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {//所有事务按照结束时间从早到晚排序
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        List<Interval> ans = new ArrayList<>();
        Interval lastSelectedInterval = null;

        for (Interval interval : intervals) {
            if (lastSelectedInterval == null || interval.start >= lastSelectedInterval.end) {
                ans.add(interval);
                lastSelectedInterval = interval;
            }
        }

        return ans;
    }
}
/*
a 0 6
b 1 4
c 3 5
d 3 8
e 4 7
f 5 9
g 6 10
h 8 11
 */