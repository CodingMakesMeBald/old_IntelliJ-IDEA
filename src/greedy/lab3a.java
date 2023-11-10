package greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lab3a {
    public static void main(String[] args) {
        QReader3a input = new QReader3a();
        QWriter3a out = new QWriter3a();
        int numberOfApple = input.nextInt();
        int numberOfBasket = input.nextInt();
        long capacity = 0L;
        int[] baskets = new int[numberOfBasket];
        for (int i = 0; i < numberOfBasket; i++) {
            baskets[i] = input.nextInt();
            capacity += baskets[i];
        }
        int[] applePosition = new int[numberOfApple];//默认升序输入
        for (int i = 0; i < numberOfApple; i++) {
            applePosition[i] = input.nextInt();
        }
        Arrays.sort(baskets);//升序排列各篮子容量
        long ans = 0;
        if (numberOfApple >= capacity){//苹果数量多于篮子总容量，篮子装满
            int positionIndex = -1;
            for (int i = 0; i < numberOfBasket; i++) {
                positionIndex += baskets[i];
                ans += (2 * applePosition[positionIndex]);
            }
        }else{//苹果数量少于篮子总容量
            int basketIndex = baskets.length - 1;//选择最大的篮子
            int positionIndex = applePosition.length - 1;//选择最远的苹果
            int appleCount = 0;//记录已选篮子能装苹果的总数
            while (appleCount < numberOfApple){
                ans += (2 * applePosition[positionIndex]);
                appleCount += baskets[basketIndex];
                positionIndex -= baskets[basketIndex];
                basketIndex--;
            }
        }
        out.println(ans);
        out.close();
    }
}
class QReader3a {
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

class QWriter3a implements Closeable {
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
/*
6 3
1 3 3
1 4 4 5 6 8
 */