package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Spring Boot main Application
 *
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {

            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

            }

            @Override
            public Validator getValidator() {
                return null;
            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

            }

            @Override
            public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {

            }

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

            }

            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

            }

            @Override
            public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

            }

            @Override
            public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {

            }

            @Override
            public MessageCodesResolver getMessageCodesResolver() {
                return null;
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {

            }

            @Override
            public void configureViewResolvers(ViewResolverRegistry registry) {

            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {

            }

            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
