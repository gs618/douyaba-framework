package com.github.douyaba.starter.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 预留
 * @author s.c.gao
 */
@Data
@ConfigurationProperties(prefix = SwaggerSecurityProperties.PREFIX)
public class SwaggerSecurityProperties {

    public static final String PREFIX = "douyaba.swagger.security";

    private boolean enabled = false;

    private Map<String, ApiKey> schemes = new HashMap<>(0);

    private String scope = "global";

    private String scopeDescription = "Global authorization setting";

    @Data
    public static class ApiKey {
        String keyName;
        String passAs = "header";
    }

}