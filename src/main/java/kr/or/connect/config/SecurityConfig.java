package kr.or.connect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 스프링 시큐리티를 이용해 로그인/로그아웃/인증/인가 등을 처리하기 위한 설정 파일입니다.
// @EnableWebSecurity가 붙어 있을 경우 스프링 시큐리티를 구성하는 기본적인 빈(Bean)들을 자동으로 구성해줍니다.
// WebSecurityConfigurerAdapter를 상속받으면,특정 메소드를 오버라이딩 함으로써 좀 더 손쉽게 설정할 수 있습니다.

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // public void configure(WebSecurity web) 메소드를 오버라이딩 하는 이유s는
    // 인증/인가가 필요 없는 경로를 설정할 필요가 있기 때문입니다.

    // 해당객체의 ignoring()메소드에 무시될 경로를 지정하면 됩니다.
    // 여기에서는 "/webjars/**"를 설정하였습니다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    // protected void configure(HttpSecurity http) 메소드를 오버라이딩 한다는 것은
    // 인증/인가에 대한 설정을 한다는 의미입니다.

    // http.csrf().disable()는 csrf()라는 기능을 끄라는 설정입니다.
    // csrf는 보안 설정 중 post방식으로 값을 전송할 때 token을 사용해야하는 보안 설정입니다.
    // csrf은 기본으로 설정되어 있는데요.
    // csrf를 사용하게 되면 보안성은 높아지지만 개발초기에는 불편함이 있다는 단점이 있습니다.
    // 그래서 csrf 기능을 끄도록 한 것입니다.

    // disable()메소드는 http(여기에선 HttpSecurity)를 리턴합니다.

    // 이말은 disable().authorizeRequests()는 http.authoriazeRequests()와 같은 의미를 가집니다.
    // 위의 설정은 "/"와 "/main" 경로는 누구나 접근(permitAll)할 수 있도록 한 것이며
    // 그외의 경로는 인증을 한 후에만 접근할 수 있다는 것을 의미합니다.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/", "/main").permitAll().anyRequest().authenticated();
    }

    // 패스워드 인코더를 빈으로 등록합니다. 암호를 인코딩하거나,
    // 인코딩된 암호와 사용자가 입력한 암호가 같은 지 확인할 때 사용합니다.
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
