package cn.jdk9.upgrade;

import java.io.*;
import java.util.*;

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/22 10:32
 */
public class Demo {
    public static void main(String[] args) {
        /* 钻石操作符升级（就是泛型的操作符） */
        // JDK1.7时候的升级， 右侧可以做类型推断， 省略掉泛型的类型
        List<String> list = new ArrayList<>();
        // JDK1.9的升级， 在右侧为匿名实现类时"右侧"也可以省略掉泛型的类型（1.9以前会编译报错）
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };
        /* try-catch-finally 关闭资源 */
        // JDK1.8以前的资源关闭
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        char[] chars = new char[20];
        int len;
        try {
            if ((len = inputStreamReader.read(chars)) != -1) {
                String str = new String(chars, 0, len);
                System.out.println("in：" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // JDK1.8的资源关闭：可以将资源的“实例化代码”放入try后面的括号中， 在执行完后会自动关闭括号中的资源
        //
        char[] chars1 = new char[20];
        int len1;
        /* 资源的“实例化代码”必须放入括号内， 否则编译错误 */
        try (InputStreamReader inputStreamReader1 = new InputStreamReader(System.in)) {
            if ((len1 = inputStreamReader1.read(chars1)) != -1) {
                String str1 = new String(chars1, 0, len1);
                System.out.println("in：" + str1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JDK1.9的资源关闭增加：资源的“实例化代码”放入括号外面， 在try的范围内资源是final的, 修改则编译出错
        char[] chars2 = new char[20];
        int len2;
        InputStreamReader inputStreamReader2 = new InputStreamReader(System.in);
        /* 资源的“实例化代码”可以放入括号外面，多个资源用逗号隔开， 在try的范围内次资源是final， 不能被修改 */
        try (inputStreamReader2) {
            if ((len2 = inputStreamReader2.read(chars2)) != -1) {
                String str2 = new String(chars2, 0, len2);
                System.out.println("in：" + str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* 创建一个只读集合（不可变集合） */
        // JDK1.9以前创建一个只读集合（不可变集合）
        List<String> namesList = new ArrayList<>();
        namesList.add("Joe");
        namesList.add("Bob");
        namesList.add("Bill");
        // 返回一个不可修改的集合
        namesList = Collections.unmodifiableList(namesList);
        System.out.println(namesList);
        // JDK1.9以前创建一个只读集合（不可变集合）
        /* 这个返回的集合也是不可变的， 返回的是Arrays类中的内部类List， 它的add方法逻辑就是抛异常 */
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        // JDK9中在接口中引入了更多方便并且意义更明确的方法， 例如
        List<Integer> list2 = List.of(1, 2, 3, 4, 5, 6);
        Map.of("A", 1, "B", 2, "C", 3);
        Set.of(1, 2, 3, 4);
        // 同样的， 这个返回的集合也为只读
    }
}
