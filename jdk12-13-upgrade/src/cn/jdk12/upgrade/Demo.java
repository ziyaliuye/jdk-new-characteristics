package cn.jdk12.upgrade;

/*
      ┌─┐       ┌─┐ + +
   ┌──┘ ┴───────┘ ┴──┐++
   │                 │
   │       ───       │++ + + +
   ███████───███████ │+
   │                 │+
   │       ─┴─       │
   │                 │
   └───┐         ┌───┘
       │         │
       │         │   + +
       │         │
       │         └──────────────┐
       │                        │
       │                        ├─┐
       │                        ┌─┘
       │                        │
       └─┐  ┐  ┌───────┬──┐  ┌──┘  + + + +
         │ ─┤ ─┤       │ ─┤ ─┤
         └──┴──┘       └──┴──┘  + + + +
        前方高能
        BUG闪开...
*/

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/23 14:13
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        /*
         * JDK12中增强了switch-case语法（在原有的匹配符 : 基础上增加了Lambda表达式符 ->）：
         *  使用 -> 的可以省去了break语句，避免了因少写break而出错， 同时兼容原有的 : 表达式（不能省掉break）
         *  -> 同时将多个case合并到一行（case穿透），显得简洁、清晰也更加优雅的表达逻辑分支
         * switch可以用变量接收分支的结果
         */
        // 输出月份所属季度
        String quarter = switch (5) {
            case 1, 2, 3 -> "第一季度....";
            case 4, 5, 6 -> "第二季度....";
            case 7, 8, 9 -> "第三季度....";
            case 10, 11, 12 -> "第四季度....";
            default -> "月份有误....";
        };
        System.out.println(quarter);

        /* 支持压缩数字格式化  */
        // 例如，在en_US语言环境中，1000可以格式化为“1K”，1000000可以格式化为“1M”
        // 具体取决于指定的样式NumberFormat.Style
        var cnf = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        System.out.println(cnf.format(10000));
        System.out.println(cnf.format(25000));
        System.out.println(cnf.format(1000000));
        System.out.println(cnf.format(1000000000));

        /* String新增transform()，indent()方法  */
        // transform(Function)此方法可以将单个String做类似于map的映射操作
        // indent(int) 将str缩进指定个空格， str有多行时每一行都缩进
        String str = "hello".transform(r -> r.toUpperCase()).transform(r -> r.indent(3));
        System.out.println(str);

        /* Files新增mismatch()方法, 可以返回两个Path对应的文件内容从第一个开始不一样的地方的索引， 如果都一样返回-1 */
        // Files是操作Path的工具类， Path是NIO2推出的替代File类的， Paths类用于实例化Path类
        FileWriter fileWriter = new FileWriter("tmp\\a.txt");
        fileWriter.write("a");
        fileWriter.write("b");
        fileWriter.write("c");
        fileWriter.close();
        FileWriter fileWriterB = new FileWriter("tmp\\b.txt");
        fileWriterB.write("a");
        fileWriterB.write("1");
        fileWriterB.write("c");
        fileWriterB.close();
        System.out.println(Files.mismatch(Path.of("tmp/a.txt"), Path.of("tmp/b.txt")));
    }
}
