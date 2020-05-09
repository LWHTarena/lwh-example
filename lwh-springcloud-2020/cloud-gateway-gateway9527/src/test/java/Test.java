import java.time.ZonedDateTime;

/**
 * @author liwh
 * @Title: Test
 * @Package PACKAGE_NAME
 * @Description: 获取时间戳
 * @Version 1.0.0
 * @date 2020/5/9 21:33
 */
public class Test {
    public static void main(String[] args) {
        ZonedDateTime time =ZonedDateTime.now();//默认时区
        System.out.println(time);//2020-05-09T21:34:39.200+08:00[Asia/Shanghai]
    }
}
