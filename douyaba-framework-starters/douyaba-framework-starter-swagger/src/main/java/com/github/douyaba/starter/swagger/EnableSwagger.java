package com.github.douyaba.starter.swagger;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(SwaggerAutoConfiguration.class)
public @interface EnableSwagger {
}
