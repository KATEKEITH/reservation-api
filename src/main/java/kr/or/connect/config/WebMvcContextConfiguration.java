package kr.or.connect.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "kr.or.connect.controller", "kr.or.connect.exception", "kr.or.connect.kafka" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 uri에 대해 http://localhost:18080, http://localhost:8180 도메인은 접근을 허용한다.
        registry.addMapping("/**").allowedOrigins("http://192.168.35.251:8081/");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }

    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("main");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // @Bean
    // public SimpleMappingExceptionResolver getExceptionResolver() {

    // SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();

    // // 지정되지 않은 예외에 대한 기본 에러페이지 입니다.
    // smer.setDefaultErrorView("/WEB-INF/views/error");

    // // 상태코드 맵핑이 없는 예외를 위한 기본 상태값 입니다.
    // smer.setDefaultStatusCode(200);

    // // 기본값이 "exception" 입니다. 예외 모돌 속성의 키값입니다. ${exception.message}
    // smer.setExceptionAttribute("exception");

    // // 하나 또는 그 이상의 예외를 리졸버에서 제외합니다. 제외된 예외는 web.xml에서 지정된 값이 적용됩니다.
    // // smer.setExcludedExceptions(UncheckException.class);

    // // 예외 클래스에 대해 에러 페이지를 지정합니다.
    // Properties mappings = new Properties();
    // mappings.setProperty("com.tistory.offbyone.exception.DatabaseException",
    // "common/error/databaseError");
    // smer.setExceptionMappings(mappings);

    // // 에러페이지에 상태코드를 지정합니다.
    // Properties statusCodes = new Properties();
    // statusCodes.setProperty("/WEB-INF/views/error", "500");
    // statusCodes.setProperty("common/error/securityError", "403");
    // statusCodes.setProperty("common/error/businessError", "200");
    // statusCodes.setProperty("common/error/ajaxError", "200");
    // smer.setStatusCodes(statusCodes);
    // return smer;

    // }

}
