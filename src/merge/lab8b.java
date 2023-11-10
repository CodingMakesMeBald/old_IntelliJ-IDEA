package merge;

import java.io.*;
import java.util.*;

public class lab8b {
    static int[] array;
    static int max;
    static int min;
    public static void main(String[] args) {
        QReader8b input = new QReader8b();
        QWriter8b out = new QWriter8b();
        int n = input.nextInt();
        ArrayList<pair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new pair(i, input.nextInt()));
        }
        Collections.sort(pairs, Comparator.comparingInt(p -> p.value));
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[pairs.get(i).index] = i;
        }

        int l = 0;
        int r = array.length - 1;
        int boundary = (max + min) / 2;
        shit(l, r,  out);

        out.println("-1 -1");
        out.println("");
        out.close();
    }
    public static void shit(int l, int r,  QWriter8b out){

        if(l >= r){
            return;
        }else{
            int mid = (l + r) / 2;
            int b1 = reverse(l, r,mid, out);
            shit( l, l + b1 - 1, out);
            shit( l + b1, r, out);
        }
    }

    public static void swap(int l, int r) {
        while (l < r){
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            l++;
            r--;
        }
    }
    public static int reverse(int l, int r, int boundary, QWriter8b out) {
        if (l == r) {
            return (array[l] <= boundary) ? 1 : 0;
        } if(r < l){
            return -99;
        }
        int mid = l + (r - l) / 2;
            int lbd = reverse(l, mid, boundary, out);
            int rbd = reverse(mid + 1, r, boundary, out);
            if (l + lbd <  mid + rbd) {
                swap(l + lbd, mid + rbd);
                out.println((l + lbd + 1) + " " + (mid + rbd + 1));
            }
            return lbd + rbd;


    }

}
class pair {
    int index;
    int value;
    public pair(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

class QReader8b {
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

class QWriter8b implements Closeable {
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

