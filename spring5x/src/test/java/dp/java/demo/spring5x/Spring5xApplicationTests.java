package dp.java.demo.spring5x;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

@SpringBootTest
public class Spring5xApplicationTests {

    @Test
    void contextLoads() {
    }


    public static void printJVM(){
        // 获取 RuntimeMXBean
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        // 获取 JVM 参数信息
        List<String> inputArguments = runtimeMXBean.getInputArguments();
        for (String argument : inputArguments) {
//            if (argument.startsWith("-Xms") || argument.startsWith("-Xmx") || argument.startsWith("-XX:MaxMetaspaceSize")) {
//                System.out.println("JVM 内存参数: " + argument);
//            }
            System.out.println("JVM 参数: " + argument);
        }
    }

    public static void printJavaV(){
        String javaVersion = System.getProperty("java.specification.version");
        System.out.println("项目设置的Java版本: " + javaVersion);
        String javaVersion2 = System.getProperty("java.version");
        System.out.println("Java 虚拟机运行时版本: " + javaVersion2);
    }
}
