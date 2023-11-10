package DSAA.lab0;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class Main {
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
                        counter = w(www, counter);
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
                        counter = b(bbb, counter);
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
                        counter = s(sss, counter);
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
                        counter = z(zzz,counter);
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
                        counter = w(www,counter);
                    }
                    //
                    counter = z(zzz,counter);
                    //
                    counter = b(bbb, counter);
                    /////////////////////////////////////////////////////////////////////////*****************
                    counter = s(sss,counter);
                    ///////////////////////////////////////////////////////////////////////////****************
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
            ////////////////////
            //雀头在b
            quetou = false;
            say = false;
            counter = 0;
            if (bb.length % 3 == 2 && ww.length % 3 == 0 && ss.length % 3 == 0 && zz.length % 3 == 0
                    && !(ww.length == 0 && ss.length == 0 && zz.length == 0)){
                for (int j = 0; j < bb.length - 1; j++) {//找雀头
                    int[] bbb = bb.clone();
                    int[] www = ww.clone();
                    int[] sss = ss.clone();
                    int[] zzz = zz.clone();

                    if (bbb[j] == bbb[j + 1] && bbb[j] != 0 && bbb[j + 1] != 0) {
                        bbb[j] = 0;
                        bbb[j + 1] = 0;

                        quetou = true;
                        counter = b(bbb, counter);
                        //
                    }
                    ///////////////////////
                    counter = w(www,counter);
                    ///////////////////////
                    counter = s(sss,counter);
                    ///////////////////////
                    counter = z(zzz,counter);
                    ///////////////////////
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
            ///////////////
            //雀头在s
            quetou = false;
            say = false;
            counter = 0;
            if (ss.length % 3 == 2 && ww.length % 3 == 0 && bb.length % 3 == 0 && zz.length % 3 == 0
                    && !(bb.length == 0 && ww.length == 0 && zz.length == 0)){
                for (int j = 0; j < ss.length - 1; j++) {//找雀头
                    int[] www = ww.clone();
                    int[] sss = ss.clone();
                    int[] zzz = zz.clone();
                    int[] bbb = bb.clone();

                    if (sss[j] == sss[j + 1] && sss[j] != 0 && sss[j + 1] != 0) {
                        sss[j] = 0;
                        sss[j + 1] = 0;

                        quetou = true;
                        counter = s(sss,counter);
                    }
                    /////////////////////////////////////////////////////////////////*******************************
                    counter = w(www,counter);
                    /////////////////////////////////////////////////////////////////*******************************
                    counter = z(zzz,counter);
                    ///////////////////////////////////////////////////////////////////////////*****************************
                    counter = b(bbb, counter);
                    ///////////////////////////////////////////////////////////////////////////////////****************************
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
            //雀头在z
            quetou = false;
            say = false;
            counter = 0;
            if (zz.length % 3 == 2 && ww.length % 3 == 0 && bb.length % 3 == 0 && ss.length % 3 == 0
                    && !(bb.length == 0 && ww.length == 0 && ss.length == 0)){
                for (int j = 0; j < zz.length - 1; j++) {//找雀头
                    int[] www = ww.clone();
                    int[] sss = ss.clone();
                    int[] zzz = zz.clone();
                    int[] bbb = bb.clone();

                    if (zzz[j] == zzz[j + 1] && zzz[j] != 0 && zzz[j + 1] != 0) {
                        zzz[j] = 0;
                        zzz[j + 1] = 0;

                        quetou = true;

                        //刻子
                        counter = z(zzz,counter);
                    }
                    //
                    counter = w(www,counter);
                    //
                    counter = b(bbb, counter);
                    //
                    counter = s(sss,counter);
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
            //////////////
            if (!in){
                System.out.println("Bad luck");
            }
        }

    }
    public static int s(int[] sss, int counter){
        int[] ss = sss.clone();
        int indexs = 0;
        int indexVals = 0;
        int index1s = 0;
        int index2s = 0;
        int css = 0;
        int cks = 0;
        for (int k = 0; k < sss.length; k++) {//顺子
            if (sss[k] != 0) {
                indexs = k;
                indexVals = sss[k];
                label:
                for (int l = k + 1; l < sss.length; l++) {
                    if (sss[l] == indexVals + 1) {
                        index1s = l;
                        for (int m = l + 1; m < sss.length; m++) {
                            if (sss[m] == indexVals + 2) {
                                index2s = m;
                                sss[indexs] = 0;
                                sss[index1s] = 0;
                                sss[index2s] = 0;
                                css++;
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
                                css++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        sss = ss.clone();
        indexxs = 0;
        indexVal1s = 0;
        index3s = 0;
        index4s = 0;
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
                                cks++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        indexs = 0;
        indexVals = 0;
        index1s = 0;
        index2s = 0;

        for (int k = 0; k < sss.length; k++) {//顺子
            if (sss[k] != 0) {
                indexs = k;
                indexVals = sss[k];
                label:
                for (int l = k + 1; l < sss.length; l++) {
                    if (sss[l] == indexVals + 1) {
                        index1s = l;
                        for (int m = l + 1; m < sss.length; m++) {
                            if (sss[m] == indexVals + 2) {
                                index2s = m;
                                sss[indexs] = 0;
                                sss[index1s] = 0;
                                sss[index2s] = 0;
                                cks++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        int cssf = 0;
        sss = ss.clone();
        for (int k = sss.length - 1; k >= 0; k--) {//顺子
            if (sss[k] != 0) {
                indexs = k;
                indexVals = sss[k];
                label:
                for (int l = k - 1; l >= 0; l--) {
                    if (sss[l] == indexVals - 1) {
                        index1s = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (sss[m] == indexVals - 2) {
                                index2s = m;
                                sss[indexs] = 0;
                                sss[index1s] = 0;
                                sss[index2s] = 0;
                                cssf++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        for (int k = sss.length - 1; k >= 0; k--) {
            if (sss[k] != 0) {
                indexxs = k;
                indexVal1s = sss[k];
                label1:
                for (int l = k - 1; l >= 0; l--) {
                    if (sss[l] == indexVal1s) {
                        index3s = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (sss[m] == indexVal1s) {
                                index4s = m;
                                sss[indexxs] = 0;
                                sss[index3s] = 0;
                                sss[index4s] = 0;
                                cssf++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        sss = ss.clone();
        int cksf = 0;
        for (int k = sss.length - 1; k >= 0; k--) {
            if (sss[k] != 0) {
                indexxs = k;
                indexVal1s = sss[k];
                label1:
                for (int l = k - 1; l >= 0; l--) {
                    if (sss[l] == indexVal1s) {
                        index3s = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (sss[m] == indexVal1s) {
                                index4s = m;
                                sss[indexxs] = 0;
                                sss[index3s] = 0;
                                sss[index4s] = 0;
                                cksf++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        for (int k = sss.length - 1; k >= 0; k--) {//顺子
            if (sss[k] != 0) {
                indexs = k;
                indexVals = sss[k];
                label:
                for (int l = k - 1; l >= 0; l--) {
                    if (sss[l] == indexVals - 1) {
                        index1s = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (sss[m] == indexVals - 2) {
                                index2s = m;
                                sss[indexs] = 0;
                                sss[index1s] = 0;
                                sss[index2s] = 0;
                                cksf++;
                                break label;
                            }
                        }
                    }
                }
            }
        }

        int max = css;
        if (max < cks){
            max = cks;
        }
        if (max < cssf){
            max = cssf;
        }
        if (max < cksf){
            max = cksf;
        }
        counter += max;
        return counter;
    }

    public static int b(int[] bbb, int counter){
        int[] bb = bbb.clone();
        int indexb = 0;
        int indexValb = 0;
        int index1b = 0;
        int index2b = 0;
        int csb = 0;
        int ckb = 0;
        for (int k = 0; k < bbb.length; k++) {//顺子
            if (bbb[k] != 0) {
                indexb = k;
                indexValb = bbb[k];
                label:
                for (int l = k + 1; l < bbb.length; l++) {
                    if (bbb[l] == indexValb + 1) {
                        index1b = l;
                        for (int m = l + 1; m < bbb.length; m++) {
                            if (bbb[m] == indexValb + 2) {
                                index2b = m;
                                bbb[indexb] = 0;
                                bbb[index1b] = 0;
                                bbb[index2b] = 0;
                                csb++;
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
                                csb++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        bbb = bb.clone();

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
                                ckb++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }

        for (int k = 0; k < bbb.length; k++) {//顺子
            if (bbb[k] != 0) {
                indexb = k;
                indexValb = bbb[k];
                label:
                for (int l = k + 1; l < bbb.length; l++) {
                    if (bbb[l] == indexValb + 1) {
                        index1b = l;
                        for (int m = l + 1; m < bbb.length; m++) {
                            if (bbb[m] == indexValb + 2) {
                                index2b = m;
                                bbb[indexb] = 0;
                                bbb[index1b] = 0;
                                bbb[index2b] = 0;
                                ckb++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        int csbf = 0;
        bbb = bb.clone();
        for (int k = bbb.length - 1; k >= 0; k--) {//顺子
            if (bbb[k] != 0) {
                indexb = k;
                indexValb = bbb[k];
                label:
                for (int l = k - 1; l >= 0; l--) {
                    if (bbb[l] == indexValb - 1) {
                        index1b = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (bbb[m] == indexValb - 2) {
                                index2b = m;
                                bbb[indexb] = 0;
                                bbb[index1b] = 0;
                                bbb[index2b] = 0;
                                csbf++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        for (int k = bbb.length - 1; k >= 0; k--) {
            if (bbb[k] != 0) {
                indexxb = k;
                indexVal1b = bbb[k];
                label1:
                for (int l = k - 1; l >= 0; l--) {
                    if (bbb[l] == indexVal1b) {
                        index3b = l;
                        for (int m = l -1; m >= 0; m--) {
                            if (bbb[m] == indexVal1b) {
                                index4b = m;
                                bbb[indexxb] = 0;
                                bbb[index3b] = 0;
                                bbb[index4b] = 0;
                                csbf++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        int ckbf = 0;
        bbb = bb.clone();
        for (int k = bbb.length - 1; k >= 0; k--) {
            if (bbb[k] != 0) {
                indexxb = k;
                indexVal1b = bbb[k];
                label1:
                for (int l = k - 1; l >= 0; l--) {
                    if (bbb[l] == indexVal1b) {
                        index3b = l;
                        for (int m = l -1; m >= 0; m--) {
                            if (bbb[m] == indexVal1b) {
                                index4b = m;
                                bbb[indexxb] = 0;
                                bbb[index3b] = 0;
                                bbb[index4b] = 0;
                                ckbf++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        for (int k = bbb.length - 1; k >= 0; k--) {//顺子
            if (bbb[k] != 0) {
                indexb = k;
                indexValb = bbb[k];
                label:
                for (int l = k - 1; l >= 0; l--) {
                    if (bbb[l] == indexValb - 1) {
                        index1b = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (bbb[m] == indexValb - 2) {
                                index2b = m;
                                bbb[indexb] = 0;
                                bbb[index1b] = 0;
                                bbb[index2b] = 0;
                                ckbf++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        int max = csb;
        if (max < ckb){
            max = ckb;
        }
        if (max < csbf){
            max = csbf;
        }
        if (max < ckbf){
            max = ckbf;
        }
        counter += max;
        return counter;
    }

    public static int w(int[] www, int counter){
        int[] ww = www.clone();
        int indexw = 0;
        int indexValw = 0;
        int index1w = 0;
        int index2w = 0;
        int csw = 0;
        int ckw = 0;
        for (int k = 0; k < www.length; k++) {//顺子
            if (www[k] != 0) {
                indexw = k;
                indexValw = www[k];
                label:
                for (int l = k + 1; l < www.length; l++) {
                    if (www[l] == indexValw + 1) {
                        index1w = l;
                        for (int m = l + 1; m < www.length; m++) {
                            if (www[m] == indexValw + 2) {
                                index2w = m;
                                www[indexw] = 0;
                                www[index1w] = 0;
                                www[index2w] = 0;
                                csw++;
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
                                csw++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        www = ww.clone();
        indexxw = 0;
        indexVal1w = 0;
        index3w = 0;
        index4w = 0;
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
                                ckw++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        indexw = 0;
        indexValw = 0;
        index1w = 0;
        index2w = 0;
        for (int k = 0; k < www.length; k++) {//顺子
            if (www[k] != 0) {
                indexw = k;
                indexValw = www[k];
                label:
                for (int l = k + 1; l < www.length; l++) {
                    if (www[l] == indexValw + 1) {
                        index1w = l;
                        for (int m = l + 1; m < www.length; m++) {
                            if (www[m] == indexValw + 2) {
                                index2w = m;
                                www[indexw] = 0;
                                www[index1w] = 0;
                                www[index2w] = 0;
                                ckw++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        www = ww.clone();
        int cswf = 0;
        for (int k = www.length - 1; k >= 0; k--) {//顺子
            if (www[k] != 0) {
                indexw = k;
                indexValw = www[k];
                label:
                for (int l = k - 1; l >= 0; l--) {
                    if (www[l] == indexValw - 1) {
                        index1w = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (www[m] == indexValw - 2) {
                                index2w = m;
                                www[indexw] = 0;
                                www[index1w] = 0;
                                www[index2w] = 0;
                                cswf++;
                                break label;
                            }
                        }
                    }
                }
            }
        }
        for (int k = www.length - 1; k >= 0; k--) {
            if (www[k] != 0) {
                indexxw = k;
                indexVal1w = www[k];
                label1:
                for (int l = k - 1; l >= 0; l--) {
                    if (www[l] == indexVal1w) {
                        index3w = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (www[m] == indexVal1w) {
                                index4w = m;
                                www[indexxw] = 0;
                                www[index3w] = 0;
                                www[index4w] = 0;
                                cswf++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        www = ww.clone();
        int ckwf = 0;
        for (int k = www.length - 1; k >= 0; k--) {
            if (www[k] != 0) {
                indexxw = k;
                indexVal1w = www[k];
                label1:
                for (int l = k - 1; l >= 0; l--) {
                    if (www[l] == indexVal1w) {
                        index3w = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (www[m] == indexVal1w) {
                                index4w = m;
                                www[indexxw] = 0;
                                www[index3w] = 0;
                                www[index4w] = 0;
                                ckwf++;
                                break label1;
                            }
                        }
                    }
                }
            }
        }
        for (int k = www.length - 1; k >= 0; k--) {//顺子
            if (www[k] != 0) {
                indexw = k;
                indexValw = www[k];
                label:
                for (int l = k - 1; l >= 0; l--) {
                    if (www[l] == indexValw - 1) {
                        index1w = l;
                        for (int m = l - 1; m >= 0; m--) {
                            if (www[m] == indexValw - 2) {
                                index2w = m;
                                www[indexw] = 0;
                                www[index1w] = 0;
                                www[index2w] = 0;
                                ckwf++;
                                break label;
                            }
                        }
                    }
                }
            }
        }

        int max = csw;
        if (max < ckw){
            max = ckw;
        }

        if (max < ckwf){
            max = ckwf;
        }
        if (max < cswf){
            max = cswf;
        }
        counter += max;
        return counter;
    }

    public static int z(int[] zzz, int counter){
        int[] zz = zzz.clone();
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
        return counter;
    }
}

