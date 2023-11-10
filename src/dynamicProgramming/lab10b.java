package dynamicProgramming;
import java.io.*;
import java.util.StringTokenizer;
public class lab10b {
    public static void main(String[] args) {
        QReader10b input = new QReader10b();
        QWriter10b out = new QWriter10b();
        int testCases = input.nextInt();
        for (int i = 0; i < testCases; i++) {
            String string = input.next();
            int length = string.length();//字符串长度
            int[][] table = new int[length][length];//-1-平局,1-Alice赢,2-Bob赢
            //table[i][j]: 字符串被Alice和Bob拿到只剩下初始索引从i到j的字串
            //假设只剩最后两个字符没拿，如果不同则Alice赢，相同则平局
            for (int j = 0; j < length - 1; j++) {
                if (string.charAt(j) != string.charAt(j + 1)){
                    table[j][j + 1] = 1;
                }else {
                    table[j][j + 1] = -1;
                }
            }
            //表中向下移动意味着当前行动者选择拿剩余字串的最左侧字符
            //表中向左移动意味着当前行动者选择拿剩余字串的最右侧字符
            for(int round = 1; round <= (length - 1) / 2; round++){//第几轮，向table右上方依次填表
                for(int j = 0; j < length - round * 2 - 1; j++){//j-第几行/string的开始索引
                    int exe = 2 * round + 1;//表中两坐标的差值
                    //Alice拿掉当前字符串的最右字符或者最左字符后，剩余字符串的最左或最右字符(Bob拿的)都比Alice拿的小，则Alice直接获胜，反之Bob直接获胜
                    if((string.charAt(j + exe) < string.charAt(j) && string.charAt(j + exe ) < string.charAt(j + exe - 1))
                    || (string.charAt(j) < string.charAt(j + 1) && string.charAt(j) < string.charAt(j + exe))){
                        table[j][j + exe] = 1;
                    }else if((string.charAt(j + exe) > string.charAt(j) || string.charAt(j + exe) > string.charAt(j + exe - 1))
                    && (string.charAt(j) > string.charAt(j + 1) || string.charAt(j) > string.charAt(j + exe))){
                        table[j][j + exe] = 2;
                    }else{
                        boolean BobWin = false;
                        //第一种情况Alice选择往下走(表中)/拿掉最左字符，Alice赢优先，但是Bob会想方设法的赢
                        if(!(string.charAt(j) > string.charAt(j + 1) || string.charAt(j) > string.charAt(j + exe))){
                            if(string.charAt(j + 1) == string.charAt(j) && table[j + 2][j + exe] == 2){//Bob选择往下走且赢
                                BobWin = true;
                            }else if(string.charAt(j) == string.charAt(j + exe) && table[j + 1][j + exe - 1] == 2){ //Bob选择往左走且赢
                                BobWin = true;
                            }else if(string.charAt(j + 1) == string.charAt(j) && table[j + 2][j + exe] == -1){ //Bob选择往下走且平局

                            }else if(string.charAt(j) == string.charAt(j + exe) && table[j + 1][j + exe - 1] == -1){ //Bob选择往左走且平局

                            }else{ //Bob怎么都赢不了，也平不了，则Alice赢了
                                table[j][j + exe] = 1;
                            }
                        }
                        //第二种情况Alice选择往左走(表中)/拿掉最右字符，Alice赢优先，但是Bob会想方设法的赢
                        if(!(string.charAt(j + exe) > string.charAt(j) || string.charAt(j + exe) > string.charAt(j + exe - 1))
                                && table[j][j + exe] != 1){
                            if((string.charAt(j + exe) == string.charAt(j + exe - 1)) && table[j][j + exe - 2] == 2){ //Bob选择往左走
                                BobWin = true;
                            }else if(string.charAt(j) == string.charAt(j + exe) && table[j + 1][j + exe - 1] == 2){ //Bob选择往下走
                                BobWin = true;
                            }else if((string.charAt(j + exe) == string.charAt(j + exe - 1)) && table[j][j + exe - 2] == -1){ //Bob选择往左走且平局

                            }else if(string.charAt(j) == string.charAt(j + exe) && table[j + 1][j + exe - 1] == -1){ //Bob选择往下走且平局

                            }else{ //Bob怎么都赢不了，则Alice赢了
                                table[j][j + exe] = 1;
                            }
                        }
                        //如果Alice没能赢，则根据bob来
                        if(table[j][j + exe] != 1){
                            if(BobWin){
                                table[j][j + exe] = 2;
                            }else{
                                table[j][j + exe] = -1;
                            }
                        }
                    }
                }
            }
            if(table[0][length - 1] == 1){//Alice赢
                out.println("Alice");
            }else if (table[0][length - 1] == -1){//平局
                out.println("Draw");
            }else{//Bob赢
                out.println("Bob");
            }
        }
        out.close();
    }
}
class QReader10b {
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
class QWriter10b implements Closeable {
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
