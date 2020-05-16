package com.github.douyaba.starter.web.protocol;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author s.c.gao
 */
@ConfigurationProperties(prefix = AutoboxingProperties.PREFIX)
@Data
public class AutoboxingProperties {

    public static final String PREFIX = "douyaba.autoboxing.ignore";

    private String classPrefix = "com.github.douyaba";

    private List<String> patterns = Arrays.asList(
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/",
            "csrf"
    );

    /**
     * USING_FEIGN
     */
    private List<String> headers = Collections.emptyList();
}
