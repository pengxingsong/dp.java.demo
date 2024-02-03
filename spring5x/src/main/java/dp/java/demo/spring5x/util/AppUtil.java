package dp.java.demo.spring5x.util;

public class AppUtil {

    /**
     * 将毫秒转为 xx天xx小时xx分xx秒xx毫秒
     *
     * @param timeMillis 毫秒
     * @return
     */
    public static String formatTimeSpanToZhStr(long timeMillis) {
        if(timeMillis<=0){
            return "0 毫秒";
        }
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = timeMillis / dd;
        long hour = (timeMillis - day * dd) / hh;
        long minute = (timeMillis - day * dd - hour * hh) / mi;
        long second = (timeMillis - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = timeMillis - day * dd - hour * hh - minute * mi - second * ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond).append("毫秒");
        }
        return sb.toString();
    }


}
