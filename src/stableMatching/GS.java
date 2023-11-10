package stableMatching;

import java.util.*;
public class GS {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        //CircularQueue boyUnmarried = new CircularQueue(n);
        Stack<Integer> boyUnmarried = new Stack<Integer>();
        // Create maps for boys and girls
        Map<String, Integer> boyIndexes = new HashMap<>();
        Map<String, Integer> girlIndexes = new HashMap<>();
        String[] boys = new String[n];
        String[] girls = new String[n];

        // Read in boy names
        for (int i = 0; i < n; i++) {
            boys[i] = input.next();
            boyIndexes.put(boys[i], i);
            boyUnmarried.push(i);
        }

        // Read in girl names
        for (int i = 0; i < n; i++) {
            girls[i] = input.next();
            girlIndexes.put(girls[i], i);
        }

        //Read in boy preferences
        int[][] boyPreference = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boyPreference[i][j] = girlIndexes.get(input.next());
            }
        }

        //Read in girl preferences
        int[][] girlPreference = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                girlPreference[i][j] = boyIndexes.get(input.next());
            }
        }

        // i 号女生第 inverseList[i][j] 喜欢 j 号男生
        int[][] inverseList = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseList[i][girlPreference[i][j]] = j;
            }
        }

        //i 号男生和 pair[i] 号女生配对
        int[] pair = new int[n];
        //i 号女生的现任
        int[] now = new int[n];
        //记录编号为 i 的男生是否有婚配
        boolean[] isBoyMarried = new boolean[n];
        //记录编号为 i 的女生是否有婚配
        boolean[] isGirlMarried = new boolean[n];
        //记录编号为 i 的男生向喜好列表中第 boyProposal[i] 个女生以前的女生都求过婚了
        int[] boyProposal = new int[n];


        while (true) {
            //所有男生已婚配，退出循环
            if (boyUnmarried.size() == 0) {
                break;
            }

            int b = boyUnmarried.pop();
            int g = boyPreference[b][boyProposal[b]];
            boyProposal[b]++;

            if (!isGirlMarried[g]) {//女生还未婚配，求婚成功
                isGirlMarried[g] = true;
                isBoyMarried[b] = true;
                pair[b] = g;
                now[g] = b;
            } else {//女生已婚配，女生比较现任与求婚者
                int cur = now[g];
                if (inverseList[g][b] < inverseList[g][cur]) {//女生更喜欢求婚者
                    isBoyMarried[b] = true;
                    isBoyMarried[cur] = false;
                    boyUnmarried.push(cur);
                    pair[cur] = -1;
                    pair[b] = g;
                    now[g] = b;
                } else {
                    boyUnmarried.push(b);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(boys[i] + " " + girls[pair[i]]);
        }


    }
}

/*
lecture
5
V W X Y Z
A B C D E
A B C D E
B C D A E
C D A B E
D A B C E
A B C D E
W X Y Z V
X Y Z V W
Y Z V W X
Z V W X Y
V W X Y Z

lab
5
V W X Y Z
A B C D E
B A D E C
D B A C E
B E C D A
A D C B E
B D A E C
Z V W Y X
X W Y V Z
W X Y Z V
V Z Y X W
Y W Z X V

 */



