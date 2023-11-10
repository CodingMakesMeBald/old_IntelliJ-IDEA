package DSAA.lab2;

public class test {
    public static void main(String[] args) {
        int[][] sumArr = {{1,1},{1,2},{1,3},{1,4}};
        int temp = 0;
        for(int i = 0; i < sumArr.length - 1; i ++){
            if(sumArr[i][0] == sumArr[i + 1][0] && sumArr[i][1] < sumArr[i + 1][1]){
                temp = sumArr[i][1];
                sumArr[i][1] = sumArr[i + 1][1];
                sumArr[i + 1][1] = temp;
            }
        }
        for (int i = 0; i < sumArr.length; i++) {
            System.out.print(sumArr[i][1] + " ");
        }
    }
    public static void quickSort(int[] array, int left, int right){
        if (left >= right){
            return;
        }
        int leftt = left;
        int rightt = right;
        int basic = array[leftt];
        while (leftt < rightt){
            while (leftt < rightt && array[rightt] > basic){
                rightt--;
            }
            if (leftt < rightt){
                array[leftt] = array[rightt];
            }
            while (leftt < rightt && array[leftt] <= basic){
                leftt++;
            }
            if (leftt < rightt){
                array[rightt] = array[leftt];
            }
            if (leftt >= rightt){
                array[leftt] = basic;
            }
        }
        quickSort(array, left, rightt - 1);
        quickSort(array, rightt + 1, right);
    }
}
