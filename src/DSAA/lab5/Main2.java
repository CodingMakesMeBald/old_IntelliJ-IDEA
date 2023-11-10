package DSAA.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        String A = in.next();
        String B = in.next();
        int k = 0;
        if (A.length() < B.length()){
            k = A.length();
        }else {
            k = B.length();
        }
        int left = 0;
        int mid = 0;
        int k1 = k;
        int k2 = k;
        //奇数
        if (check(A, B, k1)){
            out.println(k1);
            out.close();
            return;
        }else {
            while (left < k1) {
                mid = (left + k1 + 1) / 2;
                if (mid % 2 == 0){//偶数
                    mid++;
                }
                if (check(A, B, mid)) {
                    left = mid;
                } else {
                    k1 = mid - 2;
                }
            }
            k1 = left;
        }
        //偶数
        left = 0;
        mid = 0;
        while (left < k2) {
            mid = (left + k2 + 1) / 2;
            if (mid % 2 == 1){//奇数
                mid++;
            }
            if (check(A, B, mid)) {
                left = mid;
            } else {
                k2 = mid - 2;
            }
        }
            k2 = left;

        out.println(Math.max(k1, k2));
        out.close();
    }
    public static boolean check(String A, String B, int k){
        //正向哈希字符串A[k-1, ... , 0]
        int[] a1 = new int[A.length() - k + 1];//存正向哈希字符串A哈希值的数组
        //求第一个哈希值
        int temp = 1;
        a1[0] = A.charAt(0);
        for(int i = 1; i < k; i++) {
           a1[0] = a1[0] * 31 + A.charAt(i);
           temp *= 31;
        }
        //O(1)求剩下的哈希值
        for (int i = 1; i < a1.length; i++) {
            a1[i] = (a1[i - 1] - A.charAt(i - 1) * temp) * 31 + A.charAt(i + k - 1);
        }
        //out.println(Arrays.toString(a1));

        //反向哈希字符串A [0, ... , k - 1]
        int[] a2 = new int[A.length() - k + 1];//存反向哈希字符串A哈希值的数组
        //求第一个哈希值
        a2[0] = A.charAt(A.length() - 1);
        for (int i = 1; i < k; i++) {
            a2[0] = a2[0] * 31 + A.charAt(A.length()- i - 1);
        }
        //O(1)求剩下的哈希值
        for (int i = 1; i < a2.length; i++) {
            a2[i] = (a2[i - 1] - A.charAt(A.length() - i) * temp) * 31 + A.charAt(A.length() - i - k);
        }
        //out.println(Arrays.toString(a2));
        //存字符串A的长度为k的回文子串的哈希值的数组
        ArrayList<Integer> commonA = new ArrayList<>();
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] == a2[a2.length - 1 - i]){
                commonA.add(a1[i]);
            }
        }
        //排序
        Collections.sort(commonA);
        //out.println(commonA.toString());


        //正向哈希字符串B[k-1, ... , 0] <- 位权
        int[] b1 = new int[B.length() - k + 1];//存正向哈希字符串B哈希值的数组
        //求第一个哈希值
        b1[0] = B.charAt(0);
        for(int i = 1; i < k; i++) {
            b1[0] = b1[0] * 31 + B.charAt(i);
        }
        //O(1)求剩下的哈希值
        for (int i = 1; i < b1.length; i++) {
            b1[i] = (b1[i - 1] - B.charAt(i - 1) * temp) * 31 + B.charAt(i + k - 1);
        }
        //out.println(Arrays.toString(b1));
        //反向哈希字符串B [0, ... , k - 1]
        int[] b2 = new int[B.length() - k + 1];//存反向哈希字符串B哈希值的数组
        //求第一个哈希值
        b2[0] = B.charAt(B.length() - 1);
        for (int i = 1; i < k; i++) {
            b2[0] = b2[0] * 31 + B.charAt(B.length()- i - 1);
        }
        //O(1)求剩下的哈希值
        for (int i = 1; i < b2.length; i++) {
            b2[i] = (b2[i - 1] - B.charAt(B.length() - i) * temp) * 31 + B.charAt(B.length() - i - k);
        }
        //out.println(Arrays.toString(b2));
        //存字符串B的长度为k的回文子串的哈希值的数组
        ArrayList<Integer> commonB = new ArrayList<>();
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] == b2[b2.length - 1 - i]){
                commonB.add(b1[i]);
            }
        }
        //out.println(commonB.toString());

        //二分查找
        for (int i = 0; i < commonB.size(); i++) {
            if (binarySearch(commonA, 0, commonA.size() - 1, commonB.get(i))) {
                return true;
            }
        }
        //out.close();

        return false;
    }

    public static boolean binarySearch(ArrayList<Integer> arr, int left, int right, int target){
        while (left <= right){

            int mid = (left + right) / 2;
            if (target > arr.get(mid)){
                left = mid + 1;
                return binarySearch(arr, left, right, target);
            } else if (target < arr.get(mid)){
                right = mid - 1;
                return binarySearch(arr, left, right, target);
            } else {
                return true;
            }
        }
        return false;
    }
}

class QReader2 {
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

class QWriter2 implements Closeable {
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
