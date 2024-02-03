package dp.java.demo.console.common;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtil {

    public String getPrintDateNow(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(df);
    }

    public BigDecimal numAdd(BigDecimal v1,BigDecimal v2){
        if(v1==null){
            v1=BigDecimal.ZERO;
        }
        if(v2==null){
            v2 = BigDecimal.ZERO;
        }
        return v1.add(v2);
    }

    public String strJoin(String spit,String... str){
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
