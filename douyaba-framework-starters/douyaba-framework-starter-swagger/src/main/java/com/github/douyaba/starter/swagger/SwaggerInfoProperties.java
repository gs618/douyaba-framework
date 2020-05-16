package com.github.douyaba.starter.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author s.c.gao
 */
@Data
@ConfigurationProperties(prefix = SwaggerInfoProperties.PREFIX)
public class SwaggerInfoProperties {

    public static final String PREFIX = "douyaba.swagger";

    private String groupName = "douyaba";

    private String basePackage = "com.github.douyaba";

    private String author = "gaosongdalian";

    private String homepage = "https://#";

    private String email = "";

    private String title = "douyaba framework API";

    private String description = "";

    private String version = "2.0.0";

    private String license = "None";

    private String licenseUrl = "";

    private String termsOfServiceUrl = "https://#";

}