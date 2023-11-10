package linearList;

import java.io.*;
import java.util.StringTokenizer;

public class lab9abaoli {
    public static void main(String[] args) {
        QReader9aa input = new QReader9aa();
        QWriter9aa out = new QWriter9aa();
        int n = input.nextInt();
        int m = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = input.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int instruction = input.nextInt();
            if (instruction == 0){//change
                array[input.nextInt() - 1] = input.nextInt();
            }else {
                int a = input.nextInt();
                int b = input.nextInt();
                int temp = 0;
                for (int j = a - 1; j <= b - 1; j++) {
                    temp += array[j];
                }
                out.println(temp);
            }
        }
        out.close();
    }
}

class QReader9aa {
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

class QWriter9aa implements Closeable {
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

