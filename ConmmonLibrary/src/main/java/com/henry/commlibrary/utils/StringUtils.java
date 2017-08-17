package com.henry.commlibrary.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author : Henry
 * time :  2017/8/17 11:20
 * email : heneymark@gmail.com
 * description : 文字相关的方法
 */

public class StringUtils {

    /**
     * 字符串为空，则返回“”
     *
     * @param str
     * @return
     */
    public static String notNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 关键词高亮
     *
     * @param string  待匹配字符串
     * @param keyWord 关键词
     * @return
     */
    public static SpannableString colorString(String string, String keyWord) {
        SpannableString s = new SpannableString(string);

        Pattern p = Pattern.compile(keyWord);//这里的abc为关键字

        Matcher m = p.matcher(s);

        while (m.find()) {

            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }
}
