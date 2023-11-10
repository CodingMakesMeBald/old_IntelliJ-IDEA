package DSAA.lab1;



import java.util.Scanner;

public class Main {
    public static long mid;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //机器人初始坐标
        int xr = input.nextInt();
        int yr = input.nextInt();
        //cc初始坐标
        int xc = input.nextInt();
        int yc = input.nextInt();
        //一套指令机器人向四个方向移动的距离
        int u = 0;
        int d = 0;
        int l = 0;
        int r = 0;

        long numberOfInstruction = input.nextLong();
        String instruction = input.next();

        for (int i = 0; i < numberOfInstruction; i++) {
            if (instruction.charAt(i) == 'U'){
                u++;
            }
            if (instruction.charAt(i) == 'D'){
                d++;
            }
            if (instruction.charAt(i) == 'L'){
                l++;
            }
            if (instruction.charAt(i) == 'R'){
                r++;
            }
        }

        long timeMin = 0;
        long timeMax =(long)Math.pow(10,14);

        while (timeMin <= timeMax){
            mid = (timeMin + timeMax) / 2;
            //备份机器人的初始坐标
            int xrr = xr;
            int yrr = yr;
            long shang = 0;//商
            long yu = 0;//余数
            if (mid > numberOfInstruction){
                yu = mid % numberOfInstruction;
                shang = mid /numberOfInstruction;
            }
            xrr += r * shang;
            xrr -= l * shang;
            yrr += u * shang;
            yrr -= d * shang;

            for (int j = 0; j < yu; j++) {

                if (instruction.charAt(j) == 'U'){
                    yrr++;
                }
                if (instruction.charAt(j) == 'D'){
                    yrr--;
                }
                if (instruction.charAt(j) == 'L'){
                    xrr--;
                }
                if (instruction.charAt(j) == 'R'){
                    xrr++;
                }
            }
            long distance = Math.abs(xc -xrr) + Math.abs(yc - yrr);
            if (mid >= distance){//cc可移动的距离比当前时间机器人距cc初始点的距离大
                timeMax = mid - 1;
            }else {
                timeMin = mid + 1;
            }

        }
        if (timeMin == (long)Math.pow(10,14) + 1){
            System.out.println(-1);
        }else {
            System.out.println(timeMin);
        }
    }
}
/*
1 1 2 2
4
UUUU
 */