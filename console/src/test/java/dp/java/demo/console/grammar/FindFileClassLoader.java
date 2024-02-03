package dp.java.demo.console.grammar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 按.class文件加载类
 */

public class FindFileClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String classFileName) throws ClassNotFoundException {
        FileInputStream fis=null;
        ByteArrayOutputStream bos=null;
        try {
            // 读取类文件的字节码
            File file = new File("src/test/tres/"+classFileName);
            fis =  new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            byte[] bytecode = bos.toByteArray();
            // 定义类
            return defineClass(null, bytecode, 0, bytecode.length);
        } catch (Exception e) {
            throw new ClassNotFoundException(classFileName, e);
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                }catch (IOException e) {
                }
            }

        }
    }
}
