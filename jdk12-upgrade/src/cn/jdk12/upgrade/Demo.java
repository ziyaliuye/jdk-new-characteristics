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

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/23 14:13
 */
public class Demo {
    public static void main(String[] args) {
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
    }
}
