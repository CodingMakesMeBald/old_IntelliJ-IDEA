package recursion;

import java.util.Collections;
import java.util.Scanner;

public class problem4 {
    private static void Combination(String s, String prefix) {
        if (s.length() == 0) {
            System.out.println(prefix);
            return;
        }
        /*
        如果字符串s不为空，我们递归调用printCombinationsHelper方法两次。
        第一次，我们将字符串s的第一个字符加入前缀字符串prefix中，然后将新的字符串作为第一个参数传入printCombinationsHelper方法中。
        第二次，我们不加入字符串s的第一个字符，将新的字符串作为第一个参数传入printCombinationsHelper方法中。
         */
        // include the first character of s
        Combination(s.substring(1), prefix + s.charAt(0));//("",abcd)

        // exclude the first character of s
        Combination(s.substring(1), prefix);//("",bcd)
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.next();
        Combination(s,"");
    }
}
