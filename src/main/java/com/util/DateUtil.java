package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 钱逊 on 2017/5/2.
 */
public class DateUtil {
    public static Date  parse(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parseClassName(String code){
        int start = code.indexOf("public class");
        int end = code.indexOf("{",start);
        String name = code.substring(start+12,end).trim();
        return name;
    }
}
