package TopologicalGreed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) {
        QReader7b input = new QReader7b();
        int n = input.nextInt();
        int[] array = new int[n];
        int row = (int) (Math.log(n - 1) / Math.log(2));
        int[][] stTable = new int[row + 1][n - 1];
        array[0] = input.nextInt();
        for (int i = 1; i < n; i++) {
            array[i] = input.nextInt();
            stTable[0][i - 1] = Math.abs(array[i] - array[i - 1]);
        }

        for(int i = 1; i <= row; i++) {
            for(int j = 0; j + (int)Math.pow(2, i) - 1 < n - 1; j++) {
                stTable[i][j] = gcd(stTable[i - 1][j], stTable[i - 1][j + (int)Math.pow(2, i -1)]);
            }
        }

        int ans = 0;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            boolean found = false;
            for (int i = 0; i < n - mid; i++) {
                int j = i + mid - 1;
                if (j > n - 1){
                    break;
                }

                if(i >= j){
                    continue;
                }

                int k = (int) (Math.log(j - i + 1) / Math.log(2));
                int leftValue = stTable[k][i];
                int rightValue = stTable[k][j - (int) Math.pow(2, k) + 1];
                found = (gcd(leftValue, rightValue) > 1);

                if (found) {
                    break;
                }
            }

            if (found) {
                ans = mid ;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
 class QReader7b {
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

