package Test;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 一个数组,n个数字.打车,每个数字 1<=num<=4. 每辆车最多4人.
 * 求最少要多少车.
 */
public class Main {


    public static   int minCars() {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int num = in.nextInt();
        List<Integer> people = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int read = in.nextInt();
            people.add(read);
        }
        return minCars(people);
    }


    private static    int minCars(List<Integer> people) {
        if (people==null||people.size()==0) return 0;
        /**
         * 四个桶,分别收集各个队伍的人数.
         */
        ArrayList<Integer> one = new ArrayList<>();
        ArrayList<Integer> two = new ArrayList<>();
        ArrayList<Integer> three = new ArrayList<>();
        ArrayList<Integer> four = new ArrayList<>();
        for (Integer cnt : people) {
            if (cnt == 1) {
                one.add(cnt);
            } else if (cnt == 2) {
                two.add(cnt);
            } else if (cnt == 3) {
                three.add(cnt);
            } else {
                four.add(cnt);
            }
        }
        //收集到4个人的队伍,3个人的队伍,2个人的队伍,1个人的队伍.
        int minCars = 0;
        minCars += four.size(); //四个人队伍,每队一辆车.
        //考虑3个人的队伍.求出多少个3,多少个1.
        int threeSize = three.size();
        int oneSize = one.size();
        if (threeSize >= oneSize) {
            minCars += threeSize;
            oneSize = 0;
        } else {
            minCars += threeSize;
            oneSize -= threeSize; //一个人队伍剩余的数量.
        }
        //下面考虑两个人的队伍,以及剩余的一个人队伍.
        int twoSize = two.size();
        minCars += twoSize / 2;
        //twoSize 遗留的人.
        int leftTwo = twoSize % 2; //剩下来零个或者1个.
        //考虑一个人的剩余 oneSize.
        if (leftTwo == 0) {
            minCars += oneSize % 4 == 0 ? oneSize / 4 : oneSize / 4 + 1;
        } else {
            //还剩下一个2人队伍.
            if (oneSize > 2) {
                minCars++; //最后一个两人队伍消耗完毕.
                oneSize -= 2;
                minCars += (oneSize % 4 == 0 ? oneSize / 4 : oneSize / 4 + 1);
            } else {
                minCars++;
            }
        }
        return minCars;
    }

    public static void main(String[] args) {
//        List<Integer> people = Arrays.asList(4, 4, 4, 4, 4, 2);
//        System.out.println(minCars(people));
        //1+1+1+1. 1 2 4 3 3 2
        System.out.println(minCars());

    }

}
