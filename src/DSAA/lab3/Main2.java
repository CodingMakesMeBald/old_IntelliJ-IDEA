package DSAA.lab3;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();

        int number = in.nextInt();
        long money = in.nextLong();
        long priceCounter = 0;//总价
        long tempPrice = in.nextLong();
        long result = 0;
        priceCounter += tempPrice;
        shop first = new shop(1, tempPrice);//头结点
        shop cur = first;
        for (int i = 2; i <= number; i++) {
            tempPrice = in.nextLong();
            priceCounter += tempPrice;
            cur.next = new shop(i, tempPrice);
            cur = cur.next;
        }
        cur.next = first;

        long q = (long) Math.floor((double)money / priceCounter );
        money %= priceCounter;
        result += number * q;

        shop min = new shop(1, 1000000000);
        while (true){
            if (money == 0 || (money < min.price && cur.next == min)){
                break;
            }
            if (cur.next.price < min.price){
                min = cur.next;
            }
            if (cur.next.price <= money){
                money -= cur.next.price;
                result++;
            } else {
                priceCounter -= cur.next.price;//总价减去买不起的店
                number--;//总店数减一
                cur.next = cur.next.next;//删除买不起的店
                if (priceCounter != 0) {
                    q = (long) Math.floor((double) money / priceCounter);
                    money %= priceCounter;
                    result += number * q;
                    continue;
                }else {
                    out.println(result);
                    out.close();
                    return;
                }
            }
            cur = cur.next;

        }
        out.println(result);
        out.close();
    }
}
class shop{
    public int no;
    public long price;
    public shop next;

    public shop() {};
    public shop(int no, long price){
        this.no = no;
        this.price = price;
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
