package cn.jdk13.upgrade;

/*
                       .::::.
                     .::::::::.
                    :::::::::::
                 ..:::::::::::'    美女保佑、永无Bug
              '::::::::::::'
                .::::::::::
           '::::::::::::::..
                ..::::::::::::.
              ``::::::::::::::::
               ::::``:::::::::'        .:::.
              ::::'   ':::::'       .::::::::.
            .::::'      ::::     .:::::::'::::.
           .:::'       :::::  .:::::::::' ':::::.
          .::'        :::::.:::::::::'      ':::::.
         .::'         ::::::::::::::'         ``::::.
     ...:::           ::::::::::::'              ``::.
    ```` ':.          ':::::::::'                  ::::..
                       '.:::::'                    ':'````..
*/

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/23 21:10
 */
public class Demo {
    public static void main(String[] args) {
        /*
         * JDK12中增强了switch-case语法（在原有的匹配符 : 基础上增加了Lambda表达式符 ->）：
         * JDK13中进一步对switch-case语法做了增强， 可以使用yield关键字来结束switch语句
         */
        // 输出月份所属季度
        String quarter = switch (1) {
            case 1:
                yield "AA....";
            case 2:
                yield "BB....";
            case 3:
                yield "CC....";
            default:
                yield "SB....";
        };
        System.out.println(quarter);

        /*
         * 在Java中，通常需要使用String类型表达HTML，XML，SQL或JSON等格式的字符串，在进行字符串赋值时需要进行转义和连接操作
         *   然后才能编译该代码，这种表达方式难以阅读并且难以维护
         *
         * “文本块”就是指多行字符串，例如一段格式化后的xml、json等
         *   而有了文本块以后用户不需要转义，Java能自动搞定。因此，文本块将提高Java程序的可读性和可写性
         * “文本块”使用格式：使用 """ 作为文本块的开始和结束符号， 其中就可以放置多行的字符串，不需要进行任何转义
         * “文本块”和String属性一样， 字面量赋值都是常量池, 可以相互凭借， 转译字符也可以正常使用
         *
         * 《注意》：以开始分隔符的行终止符后的第一个字符开始（就是说开始的"""后面不能有内容， 需要写到下一行, 否则编译错误）
         *  以结束分隔符的行终止符后的前一个字符结束（就是说结束的内容在"""上面， 则文本会多一个换行符）
         */
        String hstr = """
            <html>
            <body>
            <p>Hello, ni shi dashabi....</p>
            </body>
            </html>
             """;
        // 文本块会自动去除多余的tab符（\t）， 但同时最后会多一个换行符(\n)， 除非挨着结束符"""写
        /*
         * 文本块会自动去除每行字符串“右侧”多余的tab符（\t）
         * 文本块会自动去除每行字符串以结束符"""“左侧”为基准多余的tab符（\t）
         *  （理解为结束符"""为一条竖线， 左侧的自动去除， 右侧的不会自动去除）
         */
        System.out.println(hstr);
    }
}
