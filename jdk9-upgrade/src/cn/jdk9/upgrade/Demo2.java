package cn.jdk9.upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/22 17:20
 */
public class Demo2 {
    public static void main(String[] args) {
        /*
         * Java的Steam API是java标准库最好的改进之一，让开发者能够快速运算，从而能够有效的利用数据并行计算
         * Java 8提供的Steam能够利用多核架构实现声明式的数据处理
         * 在Java 9中，Stream API变得更好， Stream 接口中添加了 4 个新的方法
         */
        List<Integer> list = List.of(1, 3, 2, 4, 5, 6, 8, 7);
        //takeWhile() 用于从Stream中获取一部分数据， 接收一个Predicate来进行选择
        // 在有序的Stream中， takeWhile()返回从开头开始的“尽量多”的元素（一旦出现不符合条件就停止返回元素）
        System.out.println("takeWhile：");
        list.stream().takeWhile(x -> x < 5).forEach(System.out::println);
        // dropWhile()的使用：dropWhile()的行为与takeWhile()相反， 返回剩余的元素（一旦出现不符合条件就将剩余的元素返回）
        System.out.println("dropWhile：");
        list.stream().dropWhile(x -> x < 5).forEach(System.out::println);
        // ofNullable()的使用:Java 8中Stream不能存储单个null（可以存储多个null），否则会报空指针异常
        // 而Java 9中的ofNullable方法允许我们创建一个单元素Stream，可以包含一个非空元素， 也可以创建一个空Stream
        Stream<String> stream = Stream.of(null, null);  // 非单个null， 可以正常使用
        // Stream<String> stream1 = Stream.of(null);  // 单个null， 使用时会报空指针异常
        // stream1.forEach(System.out::println);
        Stream<String> stream2 = Stream.ofNullable(null); // ofNullable()可以放入一个null，返回一个空Stream
        System.out.println("打印空的Stream：");
        stream2.forEach(System.out::println);
        // iterate()重载的使用:可以提供一个Predicate(判断条件)来指定什么时候结束迭代（不使用limit截断会形成无限流）
        // 第一个参数是初始值， 第一个Lambda表达式参数就是终止迭代的条件， 第二个Lambda表达式参数就是迭代条件
        // 可以理解为函数加了一个while循环， 第一层就是一个while中的条件， 第二层才是循环体
        System.out.println("iterate() 重载方法限制条件打印：");
        Stream.iterate(0, x -> x < 100, x -> x + 1).forEach(System.out::println);

        /*
         * 除了对 Stream 本身的扩展， Optional 和 Stream 之间的结合也得到了改进
         * 现在可以通过Optional的新方法stream()将一个Optional对象转换为一个(可能是空的)Stream对象
         */
        List<String> list1 = new ArrayList<>();
        list1.add("Tom");
        list1.add("Jerry");
        list1.add("Tim");
        // 将整个集合放入Optional中
        Optional<List<String>> optional = Optional.ofNullable(list1);
        // 通过Optional的stream()方法获取一个流
        Stream<List<String>> stream1 = optional.stream();
        // 迭代时注意flatMap和map的区别
        System.out.println("Optional Stream：");
        stream1.flatMap(x -> x.stream()).forEach(System.out::println);
    }
}
