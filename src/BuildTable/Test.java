package BuildTable;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import static com.github.stuxuhai.jpinyin.PinyinFormat.WITHOUT_TONE;

public class Test {
    public static void main(String[] args) throws PinyinException {
        String str = "你好顶点(新闻)";
        String substring = str.substring(0, str.indexOf("("));

        String result = PinyinHelper.convertToPinyinString(substring,"",WITHOUT_TONE);
        System.out.println(result);
    }



}
