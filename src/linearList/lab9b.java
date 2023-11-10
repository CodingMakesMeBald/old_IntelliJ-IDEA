package linearList;

import java.io.*;
import java.util.StringTokenizer;

public class lab9b{
    static Node root;
    public static void main(String[] args) {
        QReader9b input = new QReader9b();
        QWriter9b out = new QWriter9b();
        int n = input.nextInt();
        int m = input.nextInt(); //打印最左边的空余房间的编号
        root = buildTree(1, n);
        for(int i = 0; i < m; i++){
            int op = input.nextInt();
            if(op == 1){
                int number = input.nextInt(); //住进来多少人，优先从左边开始住，只能住连续的房间
                if(!checkIn(number, root)){
                    System.out.println(0);
                }
            }else{
                int l = input.nextInt();
                int r = input.nextInt();
                checkOut(l,r,root);
            }
        }
        out.close();
    }
    public static Node buildTree(int l, int r){
        if(l > r){
            return null;
        }
        Node cur = new Node(l, r);
        if(l == r){
            cur.leftChild = new Node(0,r);
            cur.rightChild = new Node(l,0);
        }else{
            int mid = (l + r) / 2;
            cur.leftChild = buildTree(l, mid);
            cur.rightChild = buildTree(mid + 1, r);
        }
        return cur;
    }

    public static boolean checkIn(int number, Node cur){
        if(cur.lazy){
            updateNode(cur,true);
        }
        if(cur.leftChild.lazy){
            cur.leftChild.lazy = false;
            cur.leftChild.leftChild.lazy = true;
            cur.leftChild.rightChild.lazy = true;
            cur.leftChild.leftFreeLength = Math.min(cur.leftFreeLength, cur.leftChild.right - cur.leftChild.left + 1);
            cur.leftChild.rightFreeLength = Math.max(cur.rightFreeLength - (cur.rightChild.right - cur.rightChild.left + 1), 0);
            cur.leftChild.maxLen = Math.max(cur.leftChild.leftFreeLength, cur.leftChild.rightFreeLength);
        }
        if(cur.rightChild.lazy){
            cur.rightChild.lazy = false;
            cur.rightChild.leftChild.lazy = true;
            cur.rightChild.rightChild.lazy = true;
            cur.rightChild.leftFreeLength = Math.max(cur.leftFreeLength - (cur.leftChild.right - cur.leftChild.left + 1), 0);
            cur.rightChild.rightFreeLength = Math.min(cur.rightFreeLength, cur.rightChild.right - cur.rightChild.left + 1);
            cur.rightChild.maxLen = Math.max(cur.rightChild.leftFreeLength, cur.rightChild.rightFreeLength);
        }
        if(cur.maxLen >= number){

            if(cur.leftChild.maxLen >= number){

                return checkIn(number, cur.leftChild);
            }else if(cur.leftChild.rightFreeLength + cur.rightChild.leftFreeLength >= number){
                int startPoint = cur.leftChild.right - cur.leftChild.rightFreeLength + 1;
                System.out.println(startPoint);
                checkInUpdate(startPoint, startPoint + number - 1, root);
                return true;
            }else{
                return checkIn(number,cur.rightChild);
            }
        }
        return false;
    }

    public static void checkInUpdate(int l, int r, Node cur){

        if(cur.left >= l && cur.right <= r){

            cur.leftFreeLength = 0;
            cur.rightFreeLength = 0;
            cur.maxLen = 0;

            cur.lazy = false;
            cur.leftChild.lazy = true;
            cur.rightChild.lazy = true;
        }else{
            boolean enterFlag = false;
            if(!(l > cur.leftChild.right || r < cur.leftChild.left)) {
                if(cur.leftChild.lazy){
                    cur.leftChild.lazy = false;
                    cur.leftChild.leftChild.lazy = true;
                    cur.leftChild.rightChild.lazy = true;
                    cur.leftChild.leftFreeLength = Math.min(cur.leftFreeLength, cur.leftChild.right - cur.leftChild.left + 1);
                    cur.leftChild.rightFreeLength = Math.max(cur.rightFreeLength - (cur.rightChild.right - cur.rightChild.left + 1), 0);
                    cur.leftChild.maxLen = Math.max(cur.leftChild.leftFreeLength, cur.leftChild.rightFreeLength);
                }
                checkInUpdate(l, r, cur.leftChild);
                enterFlag = true;
            }
            if(!(l > cur.rightChild.right || r < cur.rightChild.left)) {
                if(cur.rightChild.lazy){
                    cur.rightChild.lazy = false;
                    cur.rightChild.leftChild.lazy = true;
                    cur.rightChild.rightChild.lazy = true;
                    cur.rightChild.leftFreeLength = Math.max(cur.leftFreeLength - (cur.leftChild.right - cur.leftChild.left + 1), 0);
                    cur.rightChild.rightFreeLength = Math.min(cur.rightFreeLength, cur.rightChild.right - cur.rightChild.left + 1);
                    cur.rightChild.maxLen = Math.max(cur.rightChild.leftFreeLength, cur.rightChild.rightFreeLength);
                }
                checkInUpdate(l, r, cur.rightChild);
                enterFlag = true;
            }
            updateNode(cur, enterFlag);
        }

    }

    public static void checkOut(int l,int r, Node cur){

        if(cur.left >= l && cur.right <= r){

            cur.leftFreeLength = cur.right - cur.left + 1;
            cur.rightFreeLength = cur.right - cur.left + 1;
            cur.maxLen = cur.right - cur.left + 1;

            cur.leftChild.lazy = true;
            cur.rightChild.lazy = true;
        }else{
            boolean enterFlag = false;
            if(!(l > cur.leftChild.right || r < cur.leftChild.left)){

                if(cur.leftChild.lazy){
                    cur.leftChild.lazy = false;
                    cur.leftChild.leftChild.lazy = true;
                    cur.leftChild.rightChild.lazy = true;
                    cur.leftChild.leftFreeLength = Math.min(cur.leftFreeLength, cur.leftChild.right - cur.leftChild.left + 1);
                    cur.leftChild.rightFreeLength = Math.max(cur.rightFreeLength - (cur.rightChild.right - cur.rightChild.left + 1), 0);
                    cur.leftChild.maxLen = Math.max(cur.leftChild.leftFreeLength, cur.leftChild.rightFreeLength);
                }
                checkOut(l,r, cur.leftChild);
                enterFlag = true;
            }
            if(!(l > cur.rightChild.right || r < cur.rightChild.left)){

                if(cur.rightChild.lazy){
                    cur.rightChild.lazy = false;
                    cur.rightChild.leftChild.lazy = true;
                    cur.rightChild.rightChild.lazy = true;
                    cur.rightChild.leftFreeLength = Math.max(cur.leftFreeLength - (cur.leftChild.right - cur.leftChild.left + 1), 0);
                    cur.rightChild.rightFreeLength = Math.min(cur.rightFreeLength, cur.rightChild.right - cur.rightChild.left + 1);
                    cur.rightChild.maxLen = Math.max(cur.rightChild.leftFreeLength, cur.rightChild.rightFreeLength);
                }
                checkOut(l,r,cur.rightChild);
                enterFlag = true;
            }

            updateNode(cur, enterFlag);
        }
    }

    private static void updateNode(Node cur, boolean enterFlag) {
        if(enterFlag){
            if(cur.leftChild.lazy){
                cur.leftChild.lazy = false;
                cur.leftChild.leftChild.lazy = true;
                cur.leftChild.rightChild.lazy = true;
                cur.leftChild.leftFreeLength = Math.min(cur.leftFreeLength, cur.leftChild.right - cur.leftChild.left + 1);
                cur.leftChild.rightFreeLength = Math.max(cur.rightFreeLength - (cur.rightChild.right - cur.rightChild.left + 1), 0);
                cur.leftChild.maxLen = Math.max(cur.leftChild.leftFreeLength, cur.leftChild.rightFreeLength);
            }
            if(cur.rightChild.lazy){
                cur.rightChild.lazy = false;
                cur.rightChild.leftChild.lazy = true;
                cur.rightChild.rightChild.lazy = true;
                cur.rightChild.leftFreeLength = Math.max(cur.leftFreeLength - (cur.leftChild.right - cur.leftChild.left + 1), 0);
                cur.rightChild.rightFreeLength = Math.min(cur.rightFreeLength, cur.rightChild.right - cur.rightChild.left + 1);
                cur.rightChild.maxLen = Math.max(cur.rightChild.leftFreeLength, cur.rightChild.rightFreeLength);
            }
            if(cur.leftChild.leftFreeLength == cur.leftChild.right - cur.leftChild.left + 1){
                cur.leftFreeLength = cur.leftChild.leftFreeLength + cur.rightChild.leftFreeLength;
            }else{
                cur.leftFreeLength = cur.leftChild.leftFreeLength;
            }


            if(cur.rightChild.rightFreeLength == cur.rightChild.right - cur.rightChild.left + 1){
                cur.rightFreeLength = cur.rightChild.rightFreeLength + cur.leftChild.rightFreeLength;
            }else{
                cur.rightFreeLength = cur.rightChild.rightFreeLength;
            }

            cur.maxLen = Math.max(cur.leftChild.maxLen, cur.rightChild.maxLen);
            cur.maxLen = Math.max(cur.maxLen, cur.leftChild.rightFreeLength + cur.rightChild.leftFreeLength);
            cur.lazy = false;
        }
    }

}

class Node{
    int left;
    int right;
    int leftFreeLength;
    int rightFreeLength;
    int maxLen;
    Node leftChild;
    Node rightChild;
    boolean lazy;


    public Node(int l, int r){
        this.left = l;
        this.right = r;
        this.lazy = false;
        if(left != 0 && right != 0){
            leftFreeLength = right - left + 1;
            rightFreeLength = right - left + 1;
            maxLen = right - left + 1;
        }else if(left == 0){
            leftFreeLength = 0;
            maxLen = 0;
            rightFreeLength = right;
        }else if(right == 0){
            rightFreeLength = 0;
            maxLen = 0;
            leftFreeLength = left;
        }

    }
}

class QReader9b {
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

class QWriter9b implements Closeable {
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