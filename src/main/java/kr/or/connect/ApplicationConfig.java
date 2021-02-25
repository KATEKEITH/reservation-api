package kr.or.connect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kr.or.connect.config.DBConfig;

@Configuration
@Import({ DBConfig.class })
@ComponentScan(basePackages = { "kr.or.connect.dao", "kr.or.connect.service", "kr.or.connect.test" })
public class ApplicationConfig {

}
