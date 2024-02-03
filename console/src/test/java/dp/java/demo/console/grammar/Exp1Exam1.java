package dp.java.demo.console.grammar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exp1Exam1 {
    public static int count = 0;

    private static int count2 = 0;
    public Exp1Exam1() {
        System.out.println("构造函数调用");
        System.out.println("构造函数调用，公开静态字段 count："+count);
        System.out.println("构造函数调用，私有静态字段 count2："+count2);
    }
    static {
        System.out.println("静态代码块调用");
        System.out.println("静态代码块调用，公开静态字段 count："+count);
        System.out.println("静态代码块调用，私有静态字段 count2："+count2);
    }
    public String getPrintDateNow(){
        count++;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(df);
    }

    public String strJoin(String spit,String... str){
        count2++;
        StringBuilder value = new StringBuilder();
        if(str!=null){
            for (int i = 0; i <str.length ; i++) {
                String temp = str[i];
                if(temp==null){
                    continue;
                }
                if(i==str.length -1){
                    value.append(temp);
                    break;
                }
                value.append(temp).append(spit);
            }
        }
        return value.toString();
    }

}
