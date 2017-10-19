package Recursion;

public class Hanoi {
    public static void main(String[] args) {
        Hanoi.hanoi(2,'A','B','C');
    }

    public static void hanoi(int n,char src, char mid, char des ) {
        if (n == 1) {
            System.out.println(" Move "+n+" from "+src+" to "+des);
            return;

        } else {
            hanoi(n-1,src,des,mid);
            System.out.println(" Move "+n+" from "+src+" to "+des);
            hanoi(n-1,mid,src,des);
        }
    }

}
