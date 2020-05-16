package com.github.douyaba.common.util;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * @author s.c.gao
 */
public class AntMatcherUtils {

    private static final PathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    public static boolean isPathIncluded(List<String> patterns, String path) {
        for (String pattern : patterns) {
            if (ANT_PATH_MATCHER.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }

}
