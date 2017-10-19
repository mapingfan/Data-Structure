package Recursion;

/**
 * 牢记递归两个组成部分：递归终止条件（基准条件），递归调用；
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println(Factorial.factorial(15));
    }

    public static int factorial(int num) {
        if (num < 0) {
            System.out.println("The parameter is wrong.");
            return num;
        }
        if (num == 0 || num == 1) {
            return 1;
        }
        return num*factorial(num-1);
    }
}
