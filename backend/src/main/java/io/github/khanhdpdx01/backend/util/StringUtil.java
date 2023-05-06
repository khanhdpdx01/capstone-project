package io.github.khanhdpdx01.backend.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {
    public static String join(List<String> list) {
        return list.stream().collect(Collectors.joining(","));
    }

    public static List<String> split(String str) {
        return Stream.of(str.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
