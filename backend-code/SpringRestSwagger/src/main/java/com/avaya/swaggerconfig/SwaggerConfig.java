package com.avaya.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.avaya"))
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .apiInfo(custInfo());
    }
    public ApiInfo custInfo()
    {
       ApiInfo apiInfo = new ApiInfo(
               "CRM Web Connecter",  // Title
               "CRM Rest API services",      // Description 
               "1.0",                   // Version
               "TOS",                   // Terms of Service
               new Contact("Prabhakaran Manickkavachagam", "www.newtglobal.com", "prabhakaranm@newtglobal.com"), // Contact
               "JIPlicense",            // License
               "www.newtglobal"); //License URL
       
        return apiInfo;
    }
}