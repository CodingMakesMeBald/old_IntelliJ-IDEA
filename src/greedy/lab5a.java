package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class lab5a {
    public static void main(String[] args) {
        QReader5a input = new QReader5a();
        int n = input.nextInt();
        int coins = input.nextInt();
        gift[] gifts = new gift[n];

        for (int i = 0; i < n; i++) {
            gifts[i] = new gift(input.nextInt());
        }
        for (int i = 0; i < n; i++) {
            gifts[i].weight = input.nextInt();
        }
        long ans = 0L;
        PriorityQueue<Integer> cost = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int temp = gifts[i].getChange() * gifts[i].weight;
            if (gifts[i].price % 100 != 0){
                cost.add(temp);
            }

            if (coins >= gifts[i].change()){
                coins -= gifts[i].change();
            }else {
                int temp1 = cost.remove();
                coins += 100;
                coins -= gifts[i].change();
                ans += temp1;
            }
        }
        System.out.println(ans);
    }
}
/*
5 34
15 760 755 411 950
2 2 2 2 2
 */

class gift{
    int price;
    int weight;
    public gift(int price){
        this.price = price;
    }

    public int getChange(){
        int charge = 0;
        int hundred = (this.price / 100);
        hundred++;
        charge = (hundred * 100 - this.price);
        return charge;
    }
    public int change(){
        int charge = 0;
        int hundred = (this.price / 100);
        charge = (this.price - hundred * 100);
        return charge;
    }
}
class QReader5a {
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
