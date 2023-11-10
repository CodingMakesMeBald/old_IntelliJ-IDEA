package DSAA.lab1;

import java.util.Scanner;

public class test {
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

        long timeMin = 0;
        long timeMax =(long)Math.pow(10,14);

        while (timeMin <= timeMax){
            mid = (timeMin + timeMax) / 2;

            if (mid >= timeDistance(mid, instruction, xr, yr, xc, yc) ){//cc可移动的距离比当前时间机器人距cc初始点的距离大
                    timeMax = mid - 1;
            }else {
                timeMin = mid + 1;
            }

        }

        if (timeMin == Math.pow(10,14) + 1){
            System.out.println(-1);
        } else {
            if (timeMin == 0){
                System.out.println(1);
            }else {
                System.out.println(timeMin);
            }
        }




    }
    public static long timeDistance(long time, String instruction, long xr, long yr, long xc, long yc){//求经过time时间机器人距离cc初始位置的距离
        long distance = 0;
        int u = 0;
        int d = 0;
        int l = 0;
        int r = 0;
        for (int i = 0; i < instruction.length(); i++) {
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
        long shang = 0;//商
        long yu = 0;//余数
        if (time > instruction.length()){
            yu = time % instruction.length();
            shang = time /instruction.length();

            xr += r * shang;
            xr -= l * shang;
            yr += u * shang;
            yr -= d * shang;

            for (int j = 0; j < yu; j++) {

                if (instruction.charAt(j) == 'U') {
                    yr++;
                }
                if (instruction.charAt(j) == 'D') {
                    yr--;
                }
                if (instruction.charAt(j) == 'L') {
                    xr--;
                }
                if (instruction.charAt(j) == 'R') {
                    xr++;
                }
            }
        }else {
            for (int j = 0; j < instruction.length(); j++) {

                if (instruction.charAt(j) == 'U') {
                    yr++;
                }
                if (instruction.charAt(j) == 'D') {
                    yr--;
                }
                if (instruction.charAt(j) == 'L') {
                    xr--;
                }
                if (instruction.charAt(j) == 'R') {
                    xr++;
                }
            }
        }
        distance = Math.abs(xc -xr) + Math.abs(yc - yr);
        return distance;
    }
}
