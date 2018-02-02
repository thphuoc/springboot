package com.heimdall.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ListUtils {

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static String joinWithComma(List<String> strings) {
        return StringUtils.join(strings, ", ");
    }
}
