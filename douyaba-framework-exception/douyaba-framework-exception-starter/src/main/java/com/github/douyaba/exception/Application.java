package com.github.douyaba.exception;

import com.github.douyaba.starter.swagger.EnableSwagger;
import com.github.douyaba.starter.web.protocol.EnableAutoboxing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * @author s.c.gao
 */
@SpringBootApplication
@Slf4j
@EnableAutoboxing
@EnableSwagger
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        log.info(" ========== " + applicationContext.getId() + " started ==========");
    }

}
