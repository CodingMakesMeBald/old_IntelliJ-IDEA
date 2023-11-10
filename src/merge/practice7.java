package merge;

public class practice7 {
    public static void main(String[] args) {
        System.out.println(findPoint(3,8,2));
    }


    public static int findPoint(int order, int x, int y){ //order:目前是几阶
        // 1 -> 4 -> 2
        // 2 -> 8 -> 4
        // 3 -> 16 -> 8
        //如果已经是一阶了，return
        if(order == 1){
            if(x == 1 && y == 1){
                return 1;
            }else if(x == 1 && y == 2){
                return 2;
            }else if(x == 2 && y == 2){
                return 3;
            }else{
                return 4;
            }
        }else{
            //找出是哪一部分
            int midPoint = (int) Math.pow(2, order - 1);
            int part;
            if(x <= midPoint && y <= midPoint){
                //part 1 (y = x 翻转)
                int temp = x;
                x = y;
                y = temp;
                part = 1;
            }else if(x <= midPoint && y > midPoint){
                //part 2 offset -> y
                y -= midPoint;
                part = 2;
            }else if(x > midPoint && y > midPoint){
                //part 3 offset -> x, y
                x -= midPoint;
                y -= midPoint;
                part = 3;
            }else{
                //part 4 (y = -x 翻转) offset -> x
                x -= midPoint;
                int temp = x;
                x = (midPoint + 1 - y);
                y = (midPoint + 1 - temp);
                part = 4;
            }
            return midPoint * midPoint * (part - 1) + findPoint(order - 1, x, y);
        }
    }
}
/*
int findPoint(int order, int x, int y)
    if order is 1 then
        if (x, y) is (1, 1) then
            return 1
        else if (x, y) is (1, 2) then
            return 2
        else if (x, y) is (2, 2) then
            return 3
        else
            return 4
        end if
    else
        set midPoint equal 2^(order - 1)
        set part = 0
        if both x and y less or equal than midPoint then
            set temp to x
            set x to y
            set y to temp
            set part to 1
        else if x less or equal than midPoint, y larger than midPoint then
            y decrease by midPoint
            set part to 2
        else if both x and y larger than midPoint then
            x decrease by midPoint
            y decrease by midPoint
            set part to 3
        else
            x decrease by midPoint
            set temp to x;
            calculate x to (midPoint + 1 - y)
            calculate y to (midPoint + 1 - temp)
            set part = 4
        end if
    end if
    return midPoint * midPoint * (part - 1) + findPoint(order - 1, x, y)
 */
