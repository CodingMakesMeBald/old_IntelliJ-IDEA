package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class lab4a {
    public static void main(String[] args) {
        QReader4a input = new QReader4a();
        String text = input.next();
        char[] T = text.toCharArray();
        int n = input.nextInt();
        ArrayList<interval4a> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String pattern = input.next();
            char[] P = pattern.toCharArray();
            KMP(T, P, intervals);
        }
        if (intervals.size() == 0){
            System.out.println(0);
            return;
        }
        Collections.sort(intervals, new Comparator<interval4a>() {
            @Override
            public int compare(interval4a o1, interval4a o2) {
                return o1.end - o2.end;
            }
        });

        //System.out.println(intervals.toString());
        int ans = 1;
        int intervalCounter = 0;
        int intervalIndex = 0;
        int curPoint = 0;
        curPoint = intervals.get(intervalIndex).end;
        while (intervalCounter != intervals.size()){
            if (intervals.get(intervalIndex).start <= curPoint){
                intervalCounter++;
            }else {
                curPoint = intervals.get(intervalIndex).end;
                intervalCounter++;
                ans++;
            }
            intervalIndex++;
        }
        System.out.println(ans);


    }

    public static int[] nextArray(char[] P){
        int m = P.length;
        int[] pi = new int[m];
        pi[0] = 0;
        int k = 0;
        for (int i = 1; i < m ; i++) {
            while (k > 0 && P[k] != P[i]){
                k = pi[k - 1];
            }
            if (P[k] == P[i]){
                k = k + 1;
            }
            pi[i] = k;
        }
        return pi;
    }
    public static void KMP(char[] T, char[] P, ArrayList<interval4a> intervals){
        int n = T.length;
        int m = P.length;
        int[] pi = nextArray(P);
        int q = 0;//P上指针
        for (int i = 0; i < n; i++) {
            while (q > 0 && P[q] != T[i]){
                q = pi[q - 1];
            }
            if (P[q] == T[i]){
                q = q + 1;
            }
            if (q == m){
                intervals.add(new interval4a(i - m + 1, i));
                //System.out.print("Pattern occurs with shift " + (i - m + 1) + "\n");
                q = pi[q - 1];
            }
        }
    }
}
class interval4a{
    int start;
    int end;
    public interval4a(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + start + ", " + end + ')';
    }
}

class QReader4a {
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
