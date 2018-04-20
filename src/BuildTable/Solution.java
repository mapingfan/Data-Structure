package BuildTable;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import me.xuender.unidecode.Unidecode;

import java.io.*;
import java.sql.*;
import java.util.Random;
import java.util.UUID;

import static com.github.stuxuhai.jpinyin.PinyinFormat.WITHOUT_TONE;

/**
 * 数据库分表.
 */
public class Solution {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/shubo";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "320823";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, PinyinException {
        Connection conn = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        File file = new File("D:/category.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String sql;

        String tableName = "";


        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            tableName = PinyinHelper.convertToPinyinString(str.substring(0, str.indexOf("(")), "", WITHOUT_TONE);
//            System.out.println((int) tableName.charAt(0));
//            System.out.println(tableName);

            Statement statement = conn.createStatement();
            sql = " CREATE TABLE shubo_test." + tableName + " as " +
                    "select id, " +
                    "title,content,source,network_address, category,pub_date " +
                    "from shubo.news_info " +
                    "where source = '" + str + "' limit 20 ;";
            statement.execute(sql);




        }
        br.close();
        fr.close();


        // 展开结果集数据库
        // 完成后关闭
    }

}
