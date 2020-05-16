package com.github.douyaba.starter.web.protocol;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * @author s.c.gao
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(AutoboxingAutoConfiguration.class)
public @interface EnableAutoboxing {
}
