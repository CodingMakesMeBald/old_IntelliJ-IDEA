package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class lab5b {
    public static void main(String[] args) {
        QReader5b input = new QReader5b();
        int testCases = input.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = input.nextInt();
            student[] students = new student[n];
            for (int j = 0; j < n; j++) {
                students[j] = new student(input.nextInt(), input.nextInt());
            }
            comparatorStudent comparatorStudent = new comparatorStudent();
            Arrays.sort(students, comparatorStudent);
            long cost = 0L;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            int studentIndex = 0;
            while(studentIndex <= n - 1){
                int temp = students[studentIndex].m;
                while (studentIndex <= n - 1 && students[studentIndex].m == temp){
                    priorityQueue.add(students[studentIndex].c);
                    studentIndex++;
                }
                if (temp > (n - priorityQueue.size())   ){
                    int mustBuy = temp - (n - priorityQueue.size());
                    while (mustBuy > 0){
                        cost += priorityQueue.remove();
                        mustBuy--;
                    }
                }
            }
            System.out.println(cost);
        }
    }
}
class comparatorStudent implements Comparator<student> {//按m从大到小排序
    @Override
    public int compare(student s1, student s2) {
        if (s1.m < s2.m){
            return 1;
        }else if (s1.m > s2.m){
            return -1;
        }else {
            return 0;
        }
    }
}
/*
1
6
2 6
2 3
2 8
2 7
4 4
5 5
 */

class student{
    int m;
    int c;
    public student(int m, int c){
        this.m = m;
        this.c = c;
    }

}

class QReader5b {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
