
package DSAA.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static City[] tree;
    public static constraint[] constraints;
    public static boolean inValid = false;
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int n = in.nextInt();
        tree = new City[n];
        for (int i = 0; i < n; i++) {//初始化节点
            tree[i] = new City(i + 1, 1);
        }
        //暂时储存连接关系
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
        }
        //将第一个节点当作根
        City root = tree[0];
        //将树连起来
        for (int i = 0; i < n - 1; i++) {
            tree[u[i] - 1].next.add(tree[v[i] - 1]);
            tree[v[i] - 1].next.add(tree[u[i] - 1]);
        }

        //存储限制条件
        int m = in.nextInt();
        constraints = new constraint[m];
        for (int i = 0; i < m; i++) {
            constraints[i] = new constraint(in.nextInt(), in.nextInt(), in.nextInt());
        }
        int left = 0;
        int right = n;
        int mid = 0;
        while(left <= right){
             mid = (left + right) / 2;
            if (check(mid)){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        if (left <= n){
            out.println(left);
        }else {
            out.println(-1);
        }



        out.close();
    }
    static void dfs(City a){//初始化上下界
        a.isVisited = true;
        int temp = 1;
        for (int i = 0; i < a.next.size(); i++) {
            if (!a.next.get(i).isVisited ){
                a.next.get(i).level = a.level + 1;
                dfs(a.next.get(i));
                temp += a.next.get(i).upperBound;
            }
        }
        a.upperBound = temp;
    }

    static void dfsIntersection(City a){//取交集
        a.isVisited = true;
        int tempLowerBound = 0;
        int tempUpperBound = 1;
        for (int i = 0; i < a.next.size(); i++) {
            if (!a.next.get(i).isVisited ){
                dfsIntersection(a.next.get(i));
                tempLowerBound += a.next.get(i).lowerBound;
                tempUpperBound += a.next.get(i).upperBound;
            }
        }
       /* if (tempLowerBound > a.upperBound || tempUpperBound < a.lowerBound){
            inValid = true;
            return;
        }*/

        if (tempUpperBound < a.upperBound){
            a.upperBound = tempUpperBound;
        }
        if (tempLowerBound > a.lowerBound){
            a.lowerBound = tempLowerBound;
        }
        if(a.lowerBound>a.upperBound){
            inValid = true;
            return;
        }
    }


    static boolean check(int k){
        City root = tree[0];
        dfs(root);
        for (int i = 0; i < tree.length; i++) {
            tree[i].isVisited = false;
        }
        for (int i = 0; i < constraints.length; i++) {
            int x = k - constraints[i].nA;
            if (tree[constraints[i].A - 1].level < tree[constraints[i].B - 1].level){
                if (x < tree[constraints[i].B - 1].lowerBound){
                    return false;
                }
                if (x < tree[constraints[i].B - 1].upperBound){
                    tree[constraints[i].B - 1].upperBound = x;
                }
            }else{
                x=constraints[i].nA;
                if (x > tree[constraints[i].A - 1].upperBound){
                    return false;
                }
                if (x > tree[constraints[i].A - 1].lowerBound){
                    tree[constraints[i].A - 1].lowerBound = x;
                }
            }

        }
        dfsIntersection(root);
        for (int i = 0; i < tree.length; i++) {
            tree[i].isVisited = false;
        }
        if (inValid){
            inValid = false;
            return false;
        }
        if (k <= root.upperBound && k >= root.lowerBound){
            return true;
        }else {
            return false;
        }

    }
}

class City{
    int no;
    int upperBound;
    int lowerBound;
    boolean isVisited;
    int level;

    ArrayList<City> next = new ArrayList<>();
    public City(){}
    public City(int no, int upperBound){
        this.no = no;
        this.upperBound = upperBound;
    }
}

class constraint{
    int A;
    int B;
    int nA;

    public constraint(int A, int B, int nA){
        this.A = A;
        this.B = B;
        this.nA = nA;
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
