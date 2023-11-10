package DSAA.lab8;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        boolean condition1 = false;
        boolean condition2 = true;
        boolean condition3 = false;
        boolean condition4 = true;
        int n = in.nextInt();
        long sumAi = 0;
        Integer[] array1 = new Integer[n + 2];
        array1[n + 1] = 0;
        array1[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            array1[i] = in.nextInt();
            sumAi += (long)array1[i];
            if (array1[i] <= 0){
                condition4 = false;
            }
        }
        Arrays.sort(array1, 1, n + 1);
        Integer[] array = new Integer[n + 2];
        for (int i = 1; i < n + 1; i++) {
            array[i] = array1[n + 1 - i];
        }
        array[0] = 0;
        array[n + 1] = 0;
        //后缀和数组
        long temp = 0;
        long[] postfix = new long[n + 2];
        for (int i = n; i > 0; i--) {
            temp += array[i];
            postfix[i] = temp;
        }
        if (sumAi % 2 == 0){
            condition1 = true;
        }
        if (sumAi == 2 * (long)n - 2){
            condition3 = true;
        }
        if (n == 1){
            condition4 = true;
        }

        long requirement = 0;
        long supply = 0;
        int k = n;
        for (int i = 1; i < n + 1; i++) {
            requirement += array[i];
            long part1 = (long) i * (i - 1);
            while (k > i && array[k] < i){
                k--;
            }
            if (i > k){
                k = i;
            }
            long part2 = (long) i * (k - i);
            long part3 = postfix[k + 1];
            supply = part1 + part2 + part3;
            if (supply < requirement){
                condition2 = false;
                break;
            }
        }
        if (condition1){
            out.println("YES");
        }else {
            out.println("NO");
        }

        if (condition1 && condition2){
            out.println("YES");
        }else {
            out.println("NO");
        }

        if (condition1 && condition2 && condition3 && condition4){
            out.println("YES");
        }else {
            out.println("NO");
        }

        out.close();
    }
}

class QReader {
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

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
