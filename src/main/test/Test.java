import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by 钱逊 on 2017/4/15.
 */
public class Test {
    public static void main(String[] args) throws  Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/platform?useUnicode=t" +
                "rue&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull","qx","root");
        System.out.println(conn);
    }
}
