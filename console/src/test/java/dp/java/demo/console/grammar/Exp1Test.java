package dp.java.demo.console.grammar;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class Exp1Test {
    //从 Java 9 开始，Class.forName() 方法已经过时，推荐使用 ClassLoader.loadClass() 方法
    String classPkg = "dp.java.demo.console.grammar.Exp1Exam1";

    //观察：classForNameInit，classLoaderInit的区别
    @Test
    public void classForNameInit() throws Exception {
        System.out.println("classInit 使用Class.forName："+classPkg);
        Class.forName(classPkg);
    }
    @Test
    public void classLoaderInit() throws Exception {
        System.out.println("classInit 使用ClassLoader："+classPkg);
        Thread.currentThread().getContextClassLoader().loadClass(classPkg);
    }
    @Test
    public void classForNameInvoke() throws Exception {
        System.out.println("classInvoke 使用Class.forName："+classPkg);
        runExp1Example1Class(Class.forName(classPkg));
    }

    @Test
    public void classLoaderInvoke() throws Exception {
        System.out.println("classInvoke 使用ClassLoader："+classPkg);
        Class<?> cls =Thread.currentThread().getContextClassLoader().loadClass(classPkg);
        runExp1Example1Class(cls);
    }

    private void runExp1Example1Class(Class<?> appUtilClass) {
        try {
            Object instanceObj = appUtilClass.getDeclaredConstructor().newInstance();
            System.out.println("getPrintDateNow：" +
                    appUtilClass.getMethod("getPrintDateNow")
                    .invoke(instanceObj));
            Field countField = appUtilClass.getDeclaredField("count");
            countField.setAccessible(true);
            System.out.println("count：" + countField.get(instanceObj));
            System.out.println("strJoin：" +
                    appUtilClass.getMethod("strJoin", String.class, String[].class)
                    .invoke(instanceObj, ",", new String[]{"1", "2", "3", "4"}));
            Field count2Field = appUtilClass.getDeclaredField("count2");
            count2Field.setAccessible(true);
            System.out.println("count2：" + count2Field.get(instanceObj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void findFileClassLoader(){
        try{
            System.out.println("classInit 使用findFileClassLoader：Exp1Exam2.class");
            Class<?> myClass = new FindFileClassLoader().findClass("Exp1Exam2.class");
            Object instanceObj = myClass.getDeclaredConstructor().newInstance();
            System.out.println("show：" +myClass.getMethod("show").invoke(instanceObj));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
