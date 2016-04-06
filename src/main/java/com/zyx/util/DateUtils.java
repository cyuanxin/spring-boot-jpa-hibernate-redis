package com.zyx.util;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangyuanxin on 2016/4/1.
 */
public class DateUtils {
    private final static Logger log = LoggerFactory.getLogger(DateUtils.class);
    public static final String YYYYMMDDHHMMSS = "yyyyMMddhhmmss";

    public static String dateToStr(final Date date, final String format) {
        Preconditions.checkNotNull(date);
        Preconditions.checkNotNull(format);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
//    public static Date strToDate(final String str, final String format) {
//        Preconditions.checkNotNull(str);
//        Preconditions.checkNotNull(format);
//        Date date = null;
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            log.error("str to date wrong msg: " + e);
//        }
//        return date;
//    }
}
