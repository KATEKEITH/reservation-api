package kr.or.connect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// MvcConfig는 스프링 MVC설정 파일입니다.
// 스프링 MVC설정파일에는 @EnableWebMvc가 붙어있어야 합니다. 
// @EnableWebMvc는 어노테이션 기반의 스프링 MVC를 구성할 때 필요한 Bean들을 자동으로 구성해주는 역할을 수행합니다. 
// xml로 설정할 때는 <mvc:annontation-driven/>이라는 설정을 하게 되는데 같은 설정이라고 생각하면 됩니다.

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "kr.or.connect.controller" })
public class MvcConfig implements WebMvcConfigurer {
    // DefaultServlet에 대한 설정을 합니다.
    // DispatcherServlet이 처리하지 못하는 URL은 DefaultServlet이 처리하게 됩니다.
    // 해당 설정이 없으면 자동 생성된 Swaager 페이지를 볼 수 없습니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
     * Swagger 사용 시에는 Docket Bean 을 품고있는 설정 클래스 1개가 기본으로 필요하다. Spring Boot 에서는 이
     * 기본적인 설정파일 1개로 Swagger 와 Swagger UI 를 함께 사용가능하지만, Spring MVC 의 경우 Swagger UI 를
     * 위한 별도의 설정이 필요하다. 이는, Swagger UI 를 ResourceHandler 에 수동으로 등록해야 하는 작업인데, Spring
     * Boot 에서는 이를 자동으로 설정해주지만 Spring MVC 에서는 그렇지 않기 때문이다.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()) // // 현재
                // RequestMapping으로
                // 할당된 모든 URL 리스트를
                // 추출
                .paths(PathSelectors.any())// PathSelectors.any() 를 할경우 모든 경로가 다 사용된다. RestController가 아닌 것 까지
                // 사용된다.
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Decide REST API")
                .description("This documents describes about decide version 7 REST API")
                .contact(new Contact("SIM EUN JI", "https://velog.io/@sbm3539", "simbomi06@gmail.com")).version("v1")
                .build();
    }

    /** Swagger UI 를 Resource Handler 에 등록 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/**");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
