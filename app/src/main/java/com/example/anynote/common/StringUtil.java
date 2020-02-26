package com.example.anynote.common;

public class StringUtil {

    public static String SubReplays(String str, int length, String replay) throws Exception {
        if (str.isEmpty())
            throw new Exception("字符串错误");
        if (length <= 0)
            throw new Exception("缩略长度错误");


        if (length <= str.length()) {
            String result = str.substring(0, length - (replay.length() + 1)) + replay;
            return result;
        }
        return str;
    }

    public static String SubReplays(String str, int length) throws Exception {
        return SubReplays(str, length, "...");
    }
}
