package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lab4b {
    static HashMap<Integer, activeTimeslot> map = new HashMap<Integer, activeTimeslot>();
    public static void main(String[] args) {

        QReader4b input = new QReader4b();
        long ans = 0L;
        int n = input.nextInt();
        fruit[] fruits = new fruit[n];
        for (int i = 0; i < n; i++) {
            fruits[i] = new fruit(input.nextInt(), input.nextInt(), input.nextInt());
        }
        activeTimeslot[] activeTimeslots = findActiveTimeslots(fruits);
        Arrays.sort(fruits, new comparatorValue());////水果按value从大到小排序

        for (int i = 0; i < n; i++) {
            if (linearMatch(fruits[i], map.get(fruits[i].left)) ){
                ans += fruits[i].value;
            }
        }
        System.out.println(ans);
    }

    public static activeTimeslot[] findActiveTimeslots(fruit[] fruits){//找活跃点
        Arrays.sort(fruits, new comparatorLeft());//水果按left从小到大排序
        activeTimeslot[] S = new activeTimeslot[fruits.length];
        for (int i = 0; i < S.length; i++) {
            S[i] = new activeTimeslot(-1);
        }
        int x = 0;
        for (int i = 0; i < fruits.length; i++) {
            x = Math.max(x + 1, fruits[i].left);
            if (x > 1000000000){//活跃点超过右边界，退出循环
                break;
            }
            S[i].value = x;
            map.put(x, S[i]);//value 为x的对应活跃点S[i]
            if (i > 0){
                S[i - 1].next = S[i];
            }
        }
        return S;
    }

    public static boolean linearMatch(fruit ai, activeTimeslot x){
        if (x.value > ai.right){//活跃点超过水果右边界
            return false;
        }
        if (!x.isOccupied){//value为x的活跃点空闲
            x.fruit = ai;
            x.isOccupied = true;
            ai.activeTimeslot = x;
            return true;
        }
        fruit aj = x.fruit;
        if (ai.right > aj.right){//水果i的右边界比水果j的右边界大
            return linearMatch(ai, x.next);
        }else {//水果i的右边界比水果j的右边界小或等于
            if (x.next == null){
                return false;
            }
            if (linearMatch(aj, x.next)){
                x.fruit = ai;
                x.isOccupied = true;
                ai.activeTimeslot = x;
                return true;
            }
        }
        return false;


    }
}
class comparatorLeft implements Comparator<fruit> {//按left从小到大排序
    @Override
    public int compare(fruit f1, fruit f2) {
        if (f1.left < f2.left){
            return -1;
        }else if (f1.left > f2.left){
            return 1;
        }else {
            return 0;
        }
    }
}
class comparatorValue implements Comparator<fruit> {//按value从大到小排序
    @Override
    public int compare(fruit f1, fruit f2) {
        if (f1.value < f2.value){
            return 1;
        }else if (f1.value > f2.value){
            return -1;
        }else {
            return 0;
        }
    }
}
class fruit{
    int left;
    int right;
    int value;

    activeTimeslot activeTimeslot;

    public fruit(int left, int right, int value){
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
class activeTimeslot{
    int value;
    fruit fruit;
    activeTimeslot next;
    boolean isOccupied;
    public activeTimeslot(int value){
        this.value = value;
    }
}
class QReader4b {
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
/*
5
1000000000 1000000000 1
1000000000 1000000000 1
1000000000 1000000000 1
1000000000 1000000000 1
1000000000 1000000000 1
 */
