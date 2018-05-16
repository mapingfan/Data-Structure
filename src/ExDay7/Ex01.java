package ExDay7;

/**
 * 字符串匹配算法.给定两个字符串,判断短的字符串是否在长的里面.如果在返回在长的里面的下标.
 * 不然返回-1.
 */
public class Ex01 {

    //首先写一个最简单的暴力搜索算法.

    /**
     * 找出目标串dest在src串中的位置.不存在就返回-1.
     *
     * @param src
     * @param dest
     * @return
     */
    public static int getIndexOf(String src, String dest) {
        if (src == null || dest == null || dest.length() > src.length() || dest.length() < 1) return -1;
        int srcPtr = 0;
        int destPtr = 0;
        while (srcPtr < src.length() && destPtr < dest.length()) {
            int begin = srcPtr;
            while (destPtr < dest.length() && begin < src.length() && src.charAt(begin) == dest.charAt(destPtr)) {
                begin++;
                destPtr++;
            }
            if (destPtr == dest.length()) return srcPtr;
            destPtr = 0;
            srcPtr++;
        }
        if (destPtr == dest.length()) return srcPtr;
        else return -1;
    }

    public static int getIndexOf_2(String src, String dest) {
        if (src == null || dest == null) return -1;
        if (dest.length() > src.length()) return -1;
        int backPoint = 0;
        int srcPtr = 0;
        int destPtr = 0;
        while (srcPtr < src.length() && destPtr < dest.length()) {
            backPoint = srcPtr;
            while (srcPtr < src.length() && destPtr < dest.length() && src.charAt(srcPtr) == dest.charAt(destPtr)) {
                srcPtr++;
                destPtr++;
            }
            if (destPtr == dest.length()) return backPoint;
            else {
                srcPtr = backPoint + 1;
                destPtr = 0;
            }
        }
        return -1;
    }

    //针对上面的丑陋代码继续改进一次.
    public static int getIndexOf_3(String src, String dest) {
        if (src == null || dest == null || dest.length() > src.length() || dest.length() < 1)
            return -1;
        int backPoint = 0, srcPtr = 0, destPtr = 0;
        while (backPoint < src.length() && srcPtr < src.length() && destPtr < dest.length()) {
            if (src.charAt(srcPtr) == dest.charAt(destPtr)) {
                srcPtr++;
                destPtr++;
            } else {
                srcPtr = ++backPoint;
                destPtr = 0;
            }
        }
        return destPtr == dest.length() ? srcPtr - destPtr : -1;
    }


    //kmp算法.

    /**
     *
     */
    public static int getIndexOf_kmp(String src, String dest) {
        if (src == null || dest == null) return -1;
        if (dest.length() > src.length()) return -1;
        int[] next = getNextArray(dest);
        int srcPtr = 0, destPtr = 0;
        int backPoint = 0;
        while (srcPtr < src.length() && destPtr < dest.length()) {
            backPoint = srcPtr;
            while (srcPtr < src.length() && destPtr < dest.length() && src.charAt(srcPtr) == dest.charAt(destPtr)) {
                srcPtr++;
                destPtr++;
            }
            if (srcPtr == src.length() && destPtr != dest.length()) return -1;
            if (destPtr == dest.length()) {
                return backPoint;
            } else {
                //srcPtr不动,让destPtr动起来.
                //有一种情况srcPtr需要移动.
                if (next[destPtr] != -1) {
                    destPtr = next[destPtr];
                } else {
//                    destPtr = 0;
                    srcPtr++;
                    destPtr = 0;
                }

            }
        }
        return -1;

    }

    //自己写的太丑了,还容易出问题.还是学习下左神的代码.

    public static int getIndexOf_kmp_zuo(String src, String dest) {
        if (src == null || dest == null || dest.length() > src.length() || dest.length() < 1) return -1;
        int[] next = getNextArray(dest);
        int srcPtr = 0, destPtr = 0;
        while (srcPtr < src.length() && destPtr < dest.length()) {
            if (src.charAt(srcPtr) == dest.charAt(destPtr)) {
                srcPtr++;
                destPtr++;
            } else if (next[destPtr] == -1) {
                srcPtr++;
            } else {
                destPtr = next[destPtr];
            }
        }
        return destPtr == dest.length() ? srcPtr - destPtr : -1;
    }

    private static int[] getNextArray(String dest) {
        int[] next = new int[dest.length()];
        if (dest.length() < 2) return new int[]{-1};
        next[0] = -1;
        next[1] = 0;
        //下面接着求.
        for (int i = 2; i < dest.length(); i++) {
            if (dest.charAt(i - 1) == dest.charAt(next[i - 1])) {
                next[i] = next[i - 1] + 1;
            } else {
                int jump = next[next[i - 1]];
                while (jump > 0) {
                    if (dest.charAt(jump) == dest.charAt(i - 1)) {
                        next[i] = jump + 1;
                        break;
                    } else {
                        jump = next[jump];
                    }
                }
                if (jump <= 0) next[i] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String src = "abadasasdasdadsbcab";
        String dest = "sbcab";
        System.out.println(getIndexOf_2(src, dest));
        System.out.println(getIndexOf_3(src, dest));
        System.out.println(getIndexOf_kmp_zuo(src, dest));
    }
}
