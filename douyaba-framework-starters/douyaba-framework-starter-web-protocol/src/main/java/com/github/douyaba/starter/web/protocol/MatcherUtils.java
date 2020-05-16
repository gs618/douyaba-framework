package com.github.douyaba.starter.web.protocol;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author s.c.gao
 */
class MatcherUtils {

    private static final PathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    static boolean isUrlIncluded(List<String> patterns, String path) {
        boolean result = false;
        for (String pattern : patterns) {
            if (result = ANT_PATH_MATCHER.match(pattern, path)) {
                break;
            }
        }
        return result;
    }

    static boolean ignoreHeader(List<String> headers, HttpServletRequest request) {
        boolean result = false;
        for (String header : headers) {
            if (result = StringUtils.isNotBlank(request.getHeader(header))) {
                break;
            }
        }
        return result;
    }

    static boolean ignoreHeader(List<String> headers, ServerHttpRequest request) {
        boolean result = false;
        for (String header : headers) {
            if (result = !ObjectUtils.isEmpty(request.getHeaders().get(header))) {
                break;
            }
        }
        return result;
    }

}
