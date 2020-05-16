package com.github.douyaba.starter.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author s.c.gao
 */
@Configuration
@EnableSwagger2
@Slf4j
@EnableConfigurationProperties(value = {
        SwaggerInfoProperties.class,
        SwaggerSecurityProperties.class
})
public class SwaggerAutoConfiguration {

    @Autowired
    private SwaggerInfoProperties swaggerInfoProperties;

    @Autowired
    private SwaggerSecurityProperties swaggerSecurityProperties;

    @Bean
    public Docket createRestApi() {
        log.info("---------- swagger is configuring ----------");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfoProperties.getGroupName())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(apiInfo());
        if (swaggerSecurityProperties.isEnabled()) {
            docket.securitySchemes(securitySchemes())
                    .securityContexts(securityContexts());
        }
        ApiSelectorBuilder apiBuilder = null;
        Predicate<RequestHandler> restControllerPredicate =
                RequestHandlerSelectors.withClassAnnotation(RestController.class);
        Predicate<RequestHandler> controllerPredicate = RequestHandlerSelectors.withClassAnnotation(Controller.class);
        if (StringUtils.isNotBlank(swaggerInfoProperties.getBasePackage())) {
            Predicate<RequestHandler> basePackagePredicate =
                    RequestHandlerSelectors.basePackage(swaggerInfoProperties.getBasePackage());
            docket = docket.select()
                    .apis(Predicates.and(basePackagePredicate, Predicates.or(restControllerPredicate,
                            controllerPredicate))).build();
        } else {
            docket = docket.select()
                    .apis(Predicates.or(restControllerPredicate, controllerPredicate)).build();
        }
        return docket;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> securitySchemes = new ArrayList<>(swaggerSecurityProperties.getSchemes().size());
        swaggerSecurityProperties.getSchemes().forEach((name, apikey) ->
            securitySchemes.add(new ApiKey(name,
                    apikey.getKeyName(),
                    apikey.getPassAs()))
        );
        return securitySchemes;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>(1);
        securityContexts.add(SecurityContext.builder()
                .securityReferences(securityReferences())
                .build());
        return securityContexts;
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope[] authorizationScopes = {
                new AuthorizationScope(swaggerSecurityProperties.getScope(),
                        swaggerSecurityProperties.getScopeDescription())
        };
        List<SecurityReference> securityReferences = new ArrayList<>(swaggerSecurityProperties.getSchemes().size());
        swaggerSecurityProperties.getSchemes().forEach((name, apikey) ->
            securityReferences.add(new SecurityReference(name, authorizationScopes))
        );
        return securityReferences;
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(swaggerInfoProperties.getAuthor(), swaggerInfoProperties.getHomepage(),
                swaggerInfoProperties.getEmail());
        return new ApiInfoBuilder().title(swaggerInfoProperties.getTitle())
                .description(swaggerInfoProperties.getDescription()).license(swaggerInfoProperties.getLicense())
                .licenseUrl(swaggerInfoProperties.getLicenseUrl()).contact(contact)
                .termsOfServiceUrl(swaggerInfoProperties.getTermsOfServiceUrl())
                .version(swaggerInfoProperties.getVersion()).build();
    }

}
