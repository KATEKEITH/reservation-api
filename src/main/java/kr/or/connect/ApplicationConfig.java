package kr.or.connect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kr.or.connect.config.DBConfig;

// ApplicationConfig.java는 스프링 설정파일입니다.
// @ComponentScan을 이용해 DAO와 서비스객체가 있는 페키지를 지정하고 있습니다.
// 해당 패키지에 @Repository나 @Service가 붙어 있는 클래스가 있다면 자동으로 빈(Bean)으로 생성하게 됩니다.

@Configuration
@Import({ DBConfig.class })
@ComponentScan(basePackages = { "kr.or.connect.dao", "kr.or.connect.service", "kr.or.connect.test",
        "kr.or.connect.security" })
public class ApplicationConfig {

}
