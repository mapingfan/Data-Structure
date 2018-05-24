package ExDay12.PatternMatch;

/**
 * 字符串匹配算法汇总.
 */
public class Ex01 {
    //暴力匹配算法.注释掉了比较写的丑的算法.
    /*public static int bf(String src, String dest) {
        if (src == null || dest == null || src.length() < dest.length()) return -1; //表示不匹配.
        int srcIndex = 0, destIndex = 0, backPoint = 0;
        while (backPoint < src.length()) {
            srcIndex = backPoint;
            while (srcIndex < src.length() && destIndex < dest.length() && src.charAt(srcIndex) == dest.charAt(destIndex)) {
                srcIndex++;
                destIndex++;
            }
            if (destIndex == dest.length()) {
                return backPoint;
            } else {
                destIndex = 0;
                backPoint++;
            }
        }
        return -1; //不匹配.
    }*/

    public static int bf_v1(String src, String dest) {
        if (src == null || dest == null || src.length() < dest.length()) return -1; //表示不匹配.
        int srcIndex = 0, destIndex = 0;
        while (srcIndex < src.length() && destIndex < dest.length()) {
            if (src.charAt(srcIndex) == dest.charAt(destIndex)) {
                srcIndex++;
                destIndex++;
            } else {
                destIndex = 0;
                srcIndex = srcIndex - destIndex + 1; //不需要单独设置回溯点了.可以直接利用这个信息回溯.
            }
        }
        if (destIndex == dest.length()) return srcIndex - destIndex;
        else return -1;
    }


    /**
     * rk算法,利用hash进行比较.
     */
    public static int rk(String src, String dest) {
        if (src == null || dest == null || src.length() < dest.length()) return -1; //表示不匹配.
        int aim = dest.hashCode();
        for (int i = 0; i < src.length() && i + dest.length() < src.length(); i++) {
            if (src.substring(i, i + dest.length()).hashCode() != aim) continue;
            else {
                if (isEqual(src.substring(i, i + dest.length()), dest)) return i;
                else continue;
            }
        }
        return -1;//不匹配.利用hash加速查找.
    }

    /**
     * 这个方法私有,不考虑空,长度不能的情况.
     */
    private static boolean isEqual(String substring, String dest) {
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) != dest.charAt(i)) return false;
        }
        return true;
    }


  /*  public static int kmp(String src, String dest) {
        if (src == null || dest == null || src.length() < dest.length()) return -1; //表示不匹配.
        int[] next = generateNext(dest);
        int srcIndex = 0, destIndex = 0, backPoint = 0;
        while (srcIndex < src.length()) {
            backPoint = srcIndex;
            while (srcIndex < src.length() && destIndex < dest.length() && src.charAt(srcIndex) == dest.charAt(destIndex)) {
                srcIndex++;
                destIndex++;
            }
            if (destIndex == dest.length()) {
                return backPoint;
            } else {
                if (next[destIndex] == -1) {
                    srcIndex++;
                    destIndex = 0;
                } else {
                    destIndex = next[destIndex];
                }
            }
        }
        return -1;
    }*/

    public static int kmp_v2(String src, String dest) {
        if (src == null || dest == null || src.length() < dest.length()) return -1; //表示不匹配.
        int[] next = generateNext(dest);
        int srcIndex = 0, destIndex = 0;
        while (srcIndex < src.length() && destIndex < dest.length()) {
            /*if (destIndex == -1 || src.charAt(srcIndex) == dest.charAt(destIndex)) {
                srcIndex++;
                destIndex++;
            } else {
                destIndex = next[destIndex];
            }*///优化写法.
            if (src.charAt(srcIndex) == dest.charAt(destIndex)) {
                srcIndex++;
                destIndex++;
            } else {
                if (next[destIndex] == -1) srcIndex++;
                else destIndex = next[destIndex];
            }
        }
        if (destIndex == dest.length()) return srcIndex - destIndex;
        else return -1;
    }

    private static int[] generateNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = -1;
        next[1] = 0;
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
                if (jump == 0) next[i] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String src = "abadasasdasdadsbcab";
        String dest = "tbcab";

    }
}
