import Stack.Interface.Iterator;
import Stack.StackArray.StackArray;

/**
 * implement the function of base conversion by using the stack;
 *
 */
public class BaseConversion {
    private StackArray stackArray = new StackArray();

    public static void main(String[] args) {
        BaseConversion baseConversion = new BaseConversion();
        baseConversion.baseConversion(2007);
        Iterator iterator = baseConversion.stackArray.iterator();
        while (iterator.hasNext()) {
            Object tmp = iterator.next();
            System.out.print(tmp);
        }
        System.out.println();
     BaseConversion.baseConversion_V2(2007);
    }

    public void baseConversion(int num) {
        int mod_result;
        if (num != 0) {
            while (num != 0) {
                mod_result = num % 8;
                stackArray.push(mod_result);
                num = num / 8;
            }
        } else {
            stackArray.push(0);
        }
    }

    //a recursive way to implement the function;
    public static void baseConversion_V2(int num) {
        if (num < 8) {
            System.out.print(num);
            return;
        }
        baseConversion_V2(num/8);
        System.out.print(num%8);
    }
}
