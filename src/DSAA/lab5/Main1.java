package DSAA.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1 {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int numberOfCase = in.nextInt();
        label:for (int i = 0; i < numberOfCase; i++) {
            String textString = in.next();
            int n = in.nextInt();
            String[] string = new String[n];
            for (int j = 0; j < n; j++) {
                string[j] = in.next();
            }
            //找到textString每一位能走到的最远位
            Node[] nodes = new Node[textString.length()];

            for (int j = 0; j < textString.length(); j++) {//textString的每一位
                nodes[j] = new Node();
                int maxIndex = -1;
                int maxNo = -1;
                for (int k = 0; k < string.length; k++) {//遍历string数组
                    if (j + string[k].length() > textString.length()){
                        continue;
                    }
                    for (int l = 0; l < string[k].length(); l++) {//遍历每个string的每一位
                        if (textString.charAt(j + l) != string[k].charAt(l)){
                            break;
                        }
                       if (l == string[k].length() - 1 && j + l > maxIndex){
                           maxIndex = j + l;
                           maxNo = k;
                       }
                    }
                }
                nodes[j].no = maxNo;
                nodes[j].index = maxIndex;
            }


//            for (int j = 0; j < nodes.length; j++) {
//                out.print(nodes[j].no + " " + nodes[j].index);
//                out.println("");
//            }


            if (nodes[0].index == -1){//textstring的第一位就匹配不上，返回-1
                out.println(-1);
                continue;
            }
            int left = 0;//左指针
            int right = nodes[0].index;//右指针
            ArrayList<Node> arrayList = new ArrayList<>();//万一成功染色，存放用的是哪块string和用在什么位置的数组
            arrayList.add(nodes[left]);
            while (right < textString.length() - 1){
                Node tempN = new Node();
                int temp = 0;
                for (int j = left + 1; j <= right + 1; j++) {
                    if (tempN.index < nodes[j].index){
                        tempN = nodes[j];
                        temp = j;
                    }
                }
                if (tempN.index > right){
                    arrayList.add(nodes[temp]);
                    left = temp;
                    right = tempN.index;
                }else {
                    out.println(-1);
                    continue label;
                }
            }

            out.println(arrayList.size());
            for (int j = 0; j < arrayList.size(); j++) {
                out.println(arrayList.get(j).no + 1
                        + " "
                        + (arrayList.get(j).index - string[arrayList.get(j).no].length() + 2));
            }
        }
        out.close();
    }
}
class Node{
    int no;
    int index;

    public Node(){}
    public Node(int no, int index){
        this.no = no;
        this.index = index;
    }
}
class QReader1 {
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

class QWriter1 implements Closeable {
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
