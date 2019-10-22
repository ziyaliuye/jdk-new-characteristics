package cn.jdk9.upgrade;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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
         *
         * 除了对 Stream 本身的扩展， Optional 和 Stream 之间的结合也得到了改进
         *  现在可以通过Optional的新方法stream()将一个Optional对象转换为一个(可能是空的)Stream对象
         */
        List<Integer> list = List.of(1, 3, 2, 4, 5, 6, 8, 7);
        //takeWhile() 用于从Stream中获取一部分数据， 接收一个Predicate来进行选择
        // 在有序的Stream中， takeWhile()返回从开头开始的“尽量多”的元素
        list.stream().takeWhile(x -> x > 5).forEach(System.out::println);
    }
}
