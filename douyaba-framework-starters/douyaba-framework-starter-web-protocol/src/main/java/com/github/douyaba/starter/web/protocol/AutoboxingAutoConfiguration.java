package com.github.douyaba.starter.web.protocol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author s.c.gao
 */
@Configuration
@EnableConfigurationProperties(value = {AutoboxingProperties.class})
@Slf4j
public class AutoboxingAutoConfiguration {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private AutoboxingProperties autoboxingProperties;

    @Bean
    public AutoResponseWrapper autoResponseWrapper() {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        return new AutoResponseWrapper(map.values().stream()
                .filter(handlerMethod -> handlerMethod
                        .getMethod()
                        .getDeclaringClass()
                        .getName()
                        .startsWith(autoboxingProperties.getClassPrefix()))
                .map(HandlerMethod::getMethod)
                .collect(Collectors.toList()));
    }

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;


    @PostConstruct
    public void init() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
        returnValueHandlers.add(autoResponseWrapper());
        returnValueHandlers.addAll(Objects.requireNonNull(requestMappingHandlerAdapter.getReturnValueHandlers()));
        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
    }

}