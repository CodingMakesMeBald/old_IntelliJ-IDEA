package DSAA.lab5;
import java.io.*;
import java.util.StringTokenizer;

public class Main3 {
    public static void main(String[] args) {
        QReader3 input = new QReader3();
        QWriter3 out = new QWriter3();
        String in = input.next();
        int X = 0;
        int[][] fsa = new int[in.length()][26];
        for (int j = 'a'; j <= 'z'; j++) {
            if (j == in.charAt(0)){
                fsa[0][j - 'a'] = 1;
            }else {
                fsa[0][j - 'a'] = fsa[X][j - 'a'];
            }
        }
        for (int i = 1; i < in.length(); i++) {
            for (int j = 'a'; j <= 'z'; j++) {
                if (j == in.charAt(i)){
                   fsa[i][j - 'a'] = i + 1;
                }else {
                    fsa[i][j - 'a'] = fsa[X][j - 'a'];
                }
            }
            X = fsa[X][in.charAt(i) - 'a'];

        }
        for (int i = 0; i < fsa.length; i++) {
            for (int j = 0; j < fsa[i].length; j++) {
                out.print(fsa[i][j] + " ");
            }
            out.println("");
        }
        out.close();
    }

}
class QReader3 {
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

class QWriter3 implements Closeable {
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
