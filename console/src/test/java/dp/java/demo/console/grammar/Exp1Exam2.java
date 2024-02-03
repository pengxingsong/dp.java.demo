package dp.java.demo.console.grammar;

public class Exp1Exam2 {

    public Exp1Exam2(){
        System.out.println("Exp1Exam2.class：构造函数已加载。");
    }

    static {
        System.out.println("Exp1Exam2.class：静态代码块已加载。");
    }

    public String show(){
        return "啊哈，我被调用到了。";
    }
}
