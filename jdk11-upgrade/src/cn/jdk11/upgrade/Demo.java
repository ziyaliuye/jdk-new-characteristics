package cn.jdk11.upgrade;

/*
                                       ,s555SB@@&
                                     :9H####@@@@@Xi
                                    1@@@@@@@@@@@@@@8
                                  ,8@@@@@@@@@B@@@@@@8
                                 :B@@@@X3hi8Bs;B@@@@@Ah,
            ,8i                  r@@@B:     1S ,M@@@@@@#8;
           1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S
           1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5
           ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A
            1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1
             sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1
              .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1
              s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981
              ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;
               .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS
               s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,
             :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,
           .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8
         :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:
     s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r
  ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i
 5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;
 9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:
  SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5
    ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B
          ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S
             ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#:
           .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
             ...........这特么哪个大神写的代码.....................
*/

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author lele
 * @version 1.0
 * @Description 从 JVM GC 的角度， JDK11引入了两种新的GC， 其中包括也许是划时代意义的 ZGC
 * 虽然其目前还是实验特性， 但是从能力上来看， 这是JDK的一个巨大突破， 为特定生产环境的苛刻需求提供了一个可能的选择
 * 例如，对部分企业核心存储等产品，如果能够保证不超过10ms的GC暂停，可靠性会上一个大的台阶
 * 这是过去我们进行GC调优几乎做不到的，是能与不能的问题
 * @Email 414955507@qq.com
 * @date 2019/10/22 21:24
 */
public class Demo {
    /* 代码层面的修改： */
    public static void main(String[] args) throws IOException, InterruptedException {
        /* String新增了几个方法 */
        // isBlank()：判断字符串是否为空白（\t\n都属于空白）
        System.out.println("是否为空白：" + "\t\t".isBlank());
        // strip()：去除首尾空白, trim()的增强版， 连同\t\n一起去掉
        System.out.println("\t abc \t去除尾首空白后：" + "\t abc \t".strip());
        // stripTrailing()：去除尾部空格
        System.out.println("\t abc \t去除尾部空白后：" + "\t abc \t".stripTrailing());
        // stripLeading()：去除首部空格
        System.out.println("\t abc \t去除首部空白后：" + "\t abc \t".stripLeading());
        // repeat(10)：复制字符串10次然后返回结果
        String repeatStr = "abc".repeat(10);
        System.out.println("abc重复10次结果：" + repeatStr);
        // lines().count()：返回字符串的行数（以 \n 为换行）
        System.out.println("a\nb\nc".lines().count() + "行");

        /* Optional也增加了几个方法， 现在可以很方便的将一个Optional转换成一个Stream, 或者当一个空Optional时给它一个替代的 */
        Optional<Object> optional = Optional.empty();
        // boolean isEmpty()：判断value是否为空（JDK 11）
        System.out.println("optional是否为空" + optional.isEmpty());
        System.out.println("optional是否非空" + optional.isPresent());
        // ifPresentOrElse (Consumer<?super T> action, Runnable emptyAction)：
        // value非空，执行参数1功能；如果value为空，执行参数2功能（JDK 9）
        Optional optional6 = Optional.ofNullable("wocao");
        // 说人话：如果参数e（也就是optional6本身）不为空则执行第一个函数， 如果为空则执行第二个函数
        optional6.ifPresentOrElse((e) -> System.out.println("第一个函数：" + e), () -> System.out.println("第二个函数：" + "BB"));
        // Optional<T> or​(Supplier<?extends Optional<? extends T>> supplier)：
        // value非空，返回对应的Optional；value为空，返回形参封装的Optional（JDK 9）
        Optional optional2 = Optional.empty();
        Optional optional3 = Optional.ofNullable("wocao");
        // 为空则返回optional3， 非空直接返回optional2
        System.out.println("or()返回值：" + optional2.or(() -> optional3));
        // Stream<T> stream() value非空，返回仅包含此value的Stream；否则，返回一个空的Stream（JDK 9）
        Optional optional1 = Optional.empty();
        System.out.println("空的Stream：");
        optional.stream().forEach(System.out::println);
        // T orElseThrow()：value非空，返回value；否则抛异常NoSuchElementException（JDK 10）
        optional = Optional.of("abc"); // 增加一点数据
        var obj = optional.orElseThrow();
        // 当使用这个对象值时， 如果为空，源码中直接手动抛出NoSuchElementException异常
        System.out.println(obj);

        /*
         * 局部变量推断升级：
         * 在var上添加注解的语法格式，在jdk10中是不能实现的。在JDK11中加入了这样的语法
         */
        // 在JDK11以前是不允许注解修饰的变量使用var的， @Deprecated是给结构表明过时的意思， 这里就是单纯的验证一下语法
        Consumer<String> consumer = (@Deprecated var t) -> System.out.println(t.toUpperCase());

        /*
         * 这是Java9开始引入的一个处理HTTP请求的的HTTPClientAPI，该API支持同步和异步， 而在Java11中已经为正式可用状态，类位于java.net包下
         * 它将替代仅适用于blocking模式的HttpURLConnection（HttpURLConnection是在HTTP1.0的时代创建的，并使用了协议无关的方法），并提供对WebSocket和HTTP/2的支持
         */
        /* 同步方式 */
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
        HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, responseBodyHandler);
        String body = response.body();
        System.out.println(body);
        /* 异步方式 */
        HttpClient client1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
        HttpResponse.BodyHandler<String> responseBodyHandler1 = HttpResponse.BodyHandlers.ofString();
        CompletableFuture<HttpResponse<String>> sendAsync = client.sendAsync(request, responseBodyHandler);
        sendAsync.thenApply(t -> t.body()).thenAccept(System.out::println);
    }
}
