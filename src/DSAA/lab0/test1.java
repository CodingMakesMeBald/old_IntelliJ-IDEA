package DSAA.lab0;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
//1 1w2w2w3w3w3w4w4w4w5w5w6w7w8w
public class test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        for (int i = 0; i < number; i++) {
            boolean in = false;
            String initial = input.next();
            ArrayList<Integer> w = new ArrayList<Integer>();
            ArrayList<Integer> b = new ArrayList<Integer>();
            ArrayList<Integer> s = new ArrayList<Integer>();
            ArrayList<Integer> z = new ArrayList<Integer>();//zi类型不能组成顺子
            for (int j = 1; j < initial.length(); j += 2) {
                if (initial.charAt(j) == 'w'){
                    w.add((int) (initial.charAt(j - 1) - '0'));
                } else if (initial.charAt(j) == 'b') {
                    b.add((int) (initial.charAt(j - 1) - '0'));
                }else if (initial.charAt(j) == 's') {
                    s.add((int) (initial.charAt(j - 1) - '0'));
                }else if (initial.charAt(j) == 'z') {
                    z.add((int) (initial.charAt(j - 1) - '0'));
                }
            }
            w.sort(Comparator.naturalOrder());
            b.sort(Comparator.naturalOrder());
            s.sort(Comparator.naturalOrder());
            z.sort(Comparator.naturalOrder());

            int[] ww = new int[w.size()];
            int[] bb = new int[b.size()];
            int[] ss = new int[s.size()];
            int[] zz = new int[z.size()];

            for (int j = 0; j < ww.length; j++) {
                ww[j] = w.get(j);
            }
            for (int j = 0; j < bb.length; j++) {
                bb[j] = b.get(j);
            }
            for (int j = 0; j < ss.length; j++) {
                ss[j] = s.get(j);
            }
            for (int j = 0; j < zz.length; j++) {
                zz[j] = z.get(j);
            }



            boolean quetou = false;
            boolean say = false;
            int counter = 0;
            if (ww.length == 14) {

                for (int j = 0; j < ww.length - 1; j++) {//找雀头
                    int[] www = ww.clone();

                    if (www[j] == www[j + 1] && www[j] != 0 && www[j + 1] != 0) {
                        www[j] = 0;
                        www[j + 1] = 0;

                        quetou = true;
                        int index = 0;
                        int indexVal = 0;
                        int index1 = 0;
                        int index2 = 0;
                        for (int k = 0; k < www.length; k++) {//顺子
                            if (www[k] != 0) {
                                index = k;
                                indexVal = www[k];
                                label:
                                for (int l = k + 1; l < www.length; l++) {
                                    if (www[l] == indexVal + 1) {
                                        index1 = l;
                                        for (int m = l; m < www.length; m++) {
                                            if (www[m] == indexVal + 2) {
                                                index2 = m;
                                                www[index] = 0;
                                                www[index1] = 0;
                                                www[index2] = 0;
                                                counter++;
                                                break label;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //刻子
                        int indexx = 0;
                        int indexVal1 = 0;
                        int index3 = 0;
                        int index4 = 0;
                        for (int k = 0; k < www.length; k++) {
                            if (www[k] != 0) {
                                indexx = k;
                                indexVal1 = www[k];
                                label1:
                                for (int l = k + 1; l < www.length; l++) {
                                    if (www[l] == indexVal1) {
                                        index3 = l;
                                        for (int m = l + 1; m < www.length; m++) {
                                            if (www[m] == indexVal1) {
                                                index4 = m;
                                                www[indexx] = 0;
                                                www[index3] = 0;
                                                www[index4] = 0;
                                                counter++;
                                                break label1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (counter == 4 && quetou) {
                        System.out.println("Blessing of Heaven");
                        say = true;
                        in = true;
                        break;
                    }
                    //System.out.println(counter);
                    counter = 0;
                    quetou = false;


                }
                if (!say) {
                    System.out.println("Bad luck");
                    in = true;
                }
            }

            //14个b
            if (bb.length == 14) {

                for (int j = 0; j < bb.length - 1; j++) {//找雀头
                    int[] bbb = bb.clone();

                    if (bbb[j] == bbb[j + 1] && bbb[j] != 0 && bbb[j + 1] != 0) {
                        bbb[j] = 0;
                        bbb[j + 1] = 0;

                        quetou = true;
                        int index = 0;
                        int indexVal = 0;
                        int index1 = 0;
                        int index2 = 0;
                        for (int k = 0; k < bbb.length; k++) {//顺子
                            if (bbb[k] != 0) {
                                index = k;
                                indexVal = bbb[k];
                                label:
                                for (int l = k + 1; l < bbb.length; l++) {
                                    if (bbb[l] == indexVal + 1) {
                                        index1 = l;
                                        for (int m = l; m < bbb.length; m++) {
                                            if (bbb[m] == indexVal + 2) {
                                                index2 = m;
                                                bbb[index] = 0;
                                                bbb[index1] = 0;
                                                bbb[index2] = 0;
                                                counter++;
                                                break label;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //刻子
                        int indexx = 0;
                        int indexVal1 = 0;
                        int index3 = 0;
                        int index4 = 0;
                        for (int k = 0; k < bbb.length; k++) {
                            if (bbb[k] != 0) {
                                indexx = k;
                                indexVal1 = bbb[k];
                                label1:
                                for (int l = k + 1; l < bbb.length; l++) {
                                    if (bbb[l] == indexVal1) {
                                        index3 = l;
                                        for (int m = l + 1; m < bbb.length; m++) {
                                            if (bbb[m] == indexVal1) {
                                                index4 = m;
                                                bbb[indexx] = 0;
                                                bbb[index3] = 0;
                                                bbb[index4] = 0;
                                                counter++;
                                                break label1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (counter == 4 && quetou) {
                        System.out.println("Blessing of Heaven");
                        in = true;
                        say = true;
                        break;
                    }
                    //System.out.println(counter);
                    counter = 0;
                    quetou = false;


                }
                if (!say) {
                    System.out.println("Bad luck");
                    in = true;
                }
            }

            //14个s
            if (ss.length == 14) {

                for (int j = 0; j < ss.length - 1; j++) {//找雀头
                    int[] sss = ss.clone();

                    if (sss[j] == sss[j + 1] && sss[j] != 0 && sss[j + 1] != 0) {
                        sss[j] = 0;
                        sss[j + 1] = 0;

                        quetou = true;
                        int index = 0;
                        int indexVal = 0;
                        int index1 = 0;
                        int index2 = 0;
                        for (int k = 0; k < sss.length; k++) {//顺子
                            if (sss[k] != 0) {
                                index = k;
                                indexVal = sss[k];
                                label:
                                for (int l = k + 1; l < sss.length; l++) {
                                    if (sss[l] == indexVal + 1) {
                                        index1 = l;
                                        for (int m = l; m < sss.length; m++) {
                                            if (sss[m] == indexVal + 2) {
                                                index2 = m;
                                                sss[index] = 0;
                                                sss[index1] = 0;
                                                sss[index2] = 0;
                                                counter++;
                                                break label;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //刻子
                        int indexx = 0;
                        int indexVal1 = 0;
                        int index3 = 0;
                        int index4 = 0;
                        for (int k = 0; k < sss.length; k++) {
                            if (sss[k] != 0) {
                                indexx = k;
                                indexVal1 = sss[k];
                                label1:
                                for (int l = k + 1; l < sss.length; l++) {
                                    if (sss[l] == indexVal1) {
                                        index3 = l;
                                        for (int m = l + 1; m < sss.length; m++) {
                                            if (sss[m] == indexVal1) {
                                                index4 = m;
                                                sss[indexx] = 0;
                                                sss[index3] = 0;
                                                sss[index4] = 0;
                                                counter++;
                                                break label1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (counter == 4 && quetou) {
                        System.out.println("Blessing of Heaven");
                        in = true;
                        say = true;
                        break;
                    }
                    //System.out.println(counter);
                    counter = 0;
                    quetou = false;


                }
                if (!say) {
                    System.out.println("Bad luck");
                    in = true;
                }
            }

            //14个z
            if (zz.length == 14) {

                for (int j = 0; j < zz.length - 1; j++) {//找雀头
                    int[] zzz = zz.clone();

                    if (zzz[j] == zzz[j + 1] && zzz[j] != 0 && zzz[j + 1] != 0) {
                        zzz[j] = 0;
                        zzz[j + 1] = 0;

                        quetou = true;

                        //刻子
                        int indexx = 0;
                        int indexVal1 = 0;
                        int index3 = 0;
                        int index4 = 0;
                        for (int k = 0; k < zzz.length; k++) {
                            if (zzz[k] != 0) {
                                indexx = k;
                                indexVal1 = zzz[k];
                                label1:
                                for (int l = k + 1; l < zzz.length; l++) {
                                    if (zzz[l] == indexVal1) {
                                        index3 = l;
                                        for (int m = l + 1; m < zzz.length; m++) {
                                            if (zzz[m] == indexVal1) {
                                                index4 = m;
                                                zzz[indexx] = 0;
                                                zzz[index3] = 0;
                                                zzz[index4] = 0;
                                                counter++;
                                                break label1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (counter == 4 && quetou) {
                        System.out.println("Blessing of Heaven");
                        in = true;
                        say = true;
                        break;
                    }
                    //System.out.println(counter);
                    counter = 0;
                    quetou = false;


                }
                if (!say) {
                    System.out.println("Bad luck");
                    in = true;
                }
            }
            //以上为14张牌同一花色
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //雀头在w
            quetou = false;
            say = false;
            counter = 0;
            if (ww.length % 3 == 2 && bb.length % 3 == 0 && ss.length % 3 == 0 && zz.length % 3 == 0
                    && !(bb.length == 0 && ss.length == 0 && zz.length == 0)){
                for (int j = 0; j < ww.length - 1; j++) {//找雀头
                    int[] www = ww.clone();
                    int[] sss = ss.clone();
                    int[] zzz = zz.clone();
                    int[] bbb = bb.clone();

                    if (www[j] == www[j + 1] && www[j] != 0 && www[j + 1] != 0) {
                        www[j] = 0;
                        www[j + 1] = 0;

                        quetou = true;
                        int indexw = 0;
                        int indexValw = 0;
                        int index1w = 0;
                        int index2w = 0;
                        for (int k = 0; k < www.length; k++) {//顺子
                            if (www[k] != 0) {
                                indexw = k;
                                indexValw = www[k];
                                label:
                                for (int l = k + 1; l < www.length; l++) {
                                    if (www[l] == indexValw + 1) {
                                        index1w = l;
                                        for (int m = l; m < www.length; m++) {
                                            if (www[m] == indexValw + 2) {
                                                index2w = m;
                                                www[indexw] = 0;
                                                www[index1w] = 0;
                                                www[index2w] = 0;
                                                counter++;
                                                break label;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //刻子
                        int indexxw = 0;
                        int indexVal1w = 0;
                        int index3w = 0;
                        int index4w = 0;
                        for (int k = 0; k < www.length; k++) {
                            if (www[k] != 0) {
                                indexxw = k;
                                indexVal1w = www[k];
                                label1:
                                for (int l = k + 1; l < www.length; l++) {
                                    if (www[l] == indexVal1w) {
                                        index3w = l;
                                        for (int m = l + 1; m < www.length; m++) {
                                            if (www[m] == indexVal1w) {
                                                index4w = m;
                                                www[indexxw] = 0;
                                                www[index3w] = 0;
                                                www[index4w] = 0;
                                                counter++;
                                                break label1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //
                    int indexxz = 0;
                    int indexVal1z = 0;
                    int index3z = 0;
                    int index4z = 0;
                    for (int k = 0; k < zzz.length; k++) {
                        if (zzz[k] != 0) {
                            indexxz = k;
                            indexVal1z = zzz[k];
                            label1:
                            for (int l = k + 1; l < zzz.length; l++) {
                                if (zzz[l] == indexVal1z) {
                                    index3z = l;
                                    for (int m = l + 1; m < zzz.length; m++) {
                                        if (zzz[m] == indexVal1z) {
                                            index4z = m;
                                            zzz[indexxz] = 0;
                                            zzz[index3z] = 0;
                                            zzz[index4z] = 0;
                                            counter++;
                                            break label1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //
                    int indexb = 0;
                    int indexValb = 0;
                    int index1b = 0;
                    int index2b = 0;
                    for (int k = 0; k < bbb.length; k++) {//顺子
                        if (bbb[k] != 0) {
                            indexb = k;
                            indexValb = bbb[k];
                            label:
                            for (int l = k + 1; l < bbb.length; l++) {
                                if (bbb[l] == indexValb + 1) {
                                    index1b = l;
                                    for (int m = l; m < bbb.length; m++) {
                                        if (bbb[m] == indexValb + 2) {
                                            index2b = m;
                                            bbb[indexb] = 0;
                                            bbb[index1b] = 0;
                                            bbb[index2b] = 0;
                                            counter++;
                                            break label;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //刻子
                    int indexxb = 0;
                    int indexVal1b = 0;
                    int index3b = 0;
                    int index4b = 0;
                    for (int k = 0; k < bbb.length; k++) {
                        if (bbb[k] != 0) {
                            indexxb = k;
                            indexVal1b = bbb[k];
                            label1:
                            for (int l = k + 1; l < bbb.length; l++) {
                                if (bbb[l] == indexVal1b) {
                                    index3b = l;
                                    for (int m = l + 1; m < bbb.length; m++) {
                                        if (bbb[m] == indexVal1b) {
                                            index4b = m;
                                            bbb[indexxb] = 0;
                                            bbb[index3b] = 0;
                                            bbb[index4b] = 0;
                                            counter++;
                                            break label1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //
                    int indexs = 0;
                    int indexVals = 0;
                    int index1s = 0;
                    int index2s = 0;
                    for (int k = 0; k < sss.length; k++) {//顺子
                        if (sss[k] != 0) {
                            indexs = k;
                            indexVals = sss[k];
                            label:
                            for (int l = k + 1; l < sss.length; l++) {
                                if (sss[l] == indexVals + 1) {
                                    index1s = l;
                                    for (int m = l; m < sss.length; m++) {
                                        if (sss[m] == indexVals + 2) {
                                            index2s = m;
                                            sss[indexs] = 0;
                                            sss[index1s] = 0;
                                            sss[index2s] = 0;
                                            counter++;
                                            break label;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //刻子
                    int indexxs = 0;
                    int indexVal1s = 0;
                    int index3s = 0;
                    int index4s = 0;
                    for (int k = 0; k < sss.length; k++) {
                        if (sss[k] != 0) {
                            indexxs = k;
                            indexVal1s = sss[k];
                            label1:
                            for (int l = k + 1; l < sss.length; l++) {
                                if (sss[l] == indexVal1s) {
                                    index3s = l;
                                    for (int m = l + 1; m < sss.length; m++) {
                                        if (sss[m] == indexVal1s) {
                                            index4s = m;
                                            sss[indexxs] = 0;
                                            sss[index3s] = 0;
                                            sss[index4s] = 0;
                                            counter++;
                                            break label1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //
                    if (counter == 4 && quetou) {
                        System.out.println("Blessing of Heaven");
                        in = true;
                        say = true;
                        break;
                    }
                    //System.out.println(counter);
                    counter = 0;
                    quetou = false;

                }
                if (!say) {
                    System.out.println("Bad luck");
                    in = true;
                }

            }

            if (!in){
                System.out.println("Bad luck");
            }
        }

    }
}

