package linearList;

import java.io.*;
import java.util.StringTokenizer;

public class lab9a {
    static int[] array;
    static Nodea[] tree;
    public static void main(String[] args) {
        QReader9a input = new QReader9a();
        QWriter9a out = new QWriter9a();
        int n = input.nextInt();
        int m = input.nextInt();
        array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = input.nextInt();
        }
        tree = new Nodea[4 * n];
        //建树
        buildTree(1, n, 1);
        for (int i = 0; i < m; i++) {
            int inst = input.nextInt();
            if (inst == 0){
                update(input.nextInt(), input.nextInt(), 1);
            }else {
                out.println(search(input.nextInt(), input.nextInt(), 1));
            }
        }

        out.close();

    }
/*
5
3 6 1 8 5
*/


    public static Nodea buildTree(int l, int r, int index) {
        if (l == r) {
            tree[index] = new Nodea(l, r, array[l]);
            return tree[index];
        }
        Nodea leftNode = buildTree(l, (l + r) / 2, 2 * index);
        Nodea rightNode = buildTree((l + r) / 2 + 1, r, 2 * index + 1);
        Nodea curNode = new Nodea(l, r, leftNode.sum + rightNode.sum);
        tree[index] = curNode;
        return curNode;
    }

    public static int search(int l, int r, int index) {
        if (tree[index].left >= l && tree[index].right <= r) {
            return tree[index].sum;
        } else if (tree[index].right < l || tree[index].left > r) {
            return 0;
        } else {
            int leftSum = search(l, r, index * 2);
            int rightSum = search(l, r, index * 2 + 1);
            return leftSum + rightSum;
        }
    }

    public static void update(int x, int y, int index) {
        if (tree[index].left == tree[index].right) {
            tree[index].sum = y;
            array[x] = y;
            return;
        }
        int mid = (tree[index].left + tree[index].right) / 2;
        if (x <= mid) {
            update(x, y, 2 * index);
        } else {
            update(x, y, 2 * index + 1);
        }
        tree[index].sum = tree[2 * index].sum + tree[2 * index + 1].sum;
    }




}
class Nodea{
    int left;
    int right;
    int sum;
    public Nodea(int left, int right, int sum){
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

}
class QReader9a {
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

class QWriter9a implements Closeable {
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

