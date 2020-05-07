package cn.king.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//日期类型转换
public class DateUtils {

    //日期类型转换为字符串
    public static String dateTString (Date date ,String pattern){
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String format = sf.format(date);
        return format;
    }

    //字符串类型转换为日期类型
    public static Date stringTDate(String date ,String pattern) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Date date1 = sf.parse(date);
        return date1;
    }
}
