package cn.jdk10.upgrade;

/*
     ┏┛ ┻━━━━━┛ ┻┓
     ┃　　　　　　 ┃
     ┃　　　━　　　┃
     ┃　┳┛　  ┗┳　┃
     ┃　　　　　　 ┃
     ┃　　　┻　　　┃
     ┃　　　　　　 ┃
     ┗━┓　　　┏━━━┛
       ┃　　　┃   神兽保佑
       ┃　　　┃   永无BUG..
       ┃　　　┗━━━━━━━━━┓
       ┃　　　　　　　    ┣┓
       ┃　　　　         ┏┛
       ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
         ┃ ┫ ┫   ┃ ┫ ┫
         ┗━┻━┛   ┗━┻━┛
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @author lele
 * @version 1.0
 * @Description JDK10一共定义了109个新特性， 其中包含12个JEP（对于程序员来讲， 真正的新特性其实就一个）
 * 还有一些新API和JVM规范以及JAVA语言规范上的改动
 * <p>
 * 类型推断工作原理：
 * 在处理 var时， 编译器先是查看表达式右边部分， 并根据右边变量值的类型进行推断， 作为左边变量的类型， 然后将该类型写入字节码当中
 * var不是一个关键字, 你不需要担心变量名或方法名会与var发生冲突
 * 因为 var实际上并不是一个关键字，而是一个类型名， 只有在编译器需要知道类型的地方才需要用到它
 * 除此之外， 它就是一个普通合法的标识符， 也就是说， 除了不能用它作为类名， 其他的都可以，但极少人会用它作为类名
 * <p>
 * 在字节码文件中其实这个类型时没有省略的，可以从idea工程的out目录下查看字节码文件（会自动反编译
 * @Email 414955507@qq.com
 * @date 2019/10/22 20:19
 */
public class Demo {
    public static void main(String[] args) {
        /*
         * Local-Variable Type Inference 局部变量类型推断：
         *  编写代码时， 右侧的代码往往已经决定了左边的变量类型， 显得臃肿， 新特性可以使用var代替
         * （只是在编写代码层面省略了， 编译器在编译时会自动推断并且将左边的var替换成推断后的类型）
         */
        var count = 10;
        System.out.println(count);
        var list = new ArrayList<String>();
        list.add("AA");
        list.add("BB");
        System.out.println(list);
        for (var i = 0; i < 10; i++) {
            System.out.println(i);
        }

        /*
         * 使用时需要注意Java是根据右侧代码来进行推断， 右侧是不能省略的， 不能使用: var i;
         * 并且一下场景不能使用：
         *   初始值为null：var s = null;
         *   方法引用：var r = System.out::println;
         *   Lambda表达式（无法推断左侧到底用哪个函数式接口接收）：var r = () -> Math.random();
         *   为数组静态初始化：var array = {'a', 'b', 'c'};
         *
         * 不适用于以下结构：
         *   没有初始化的局部变量声明
         *   方法的返回类型
         *   方法的参数类型
         *   构造器的参数类型
         *   属性
         *   catch块
         */

        /* JKD10中List、Set、Map接口新增的copyOf()方法， 用于复制一份集合并返回 */
        // 创建一个只读的集合
        var list1 = List.of("Java", "Python", "C#");
        // 复制一份集合并返回， 如果被复制的集合是只读的， 那么就直接返回这个集合的引用地址
        var copy1 = List.copyOf(list1);
        /* true， 被复制的集合只读集合， 直接返回它的引用地址 */
        System.out.println(list1 == copy1);
        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list2);
        /* false， 被复制的集合非只读集合， 返回一个全新的集合（只读） */
        System.out.println(list2 == copy2);

    }
}
