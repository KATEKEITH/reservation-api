package kr.or.connect.config;

import org.apache.tinkerpop.gremlin.process.computer.GraphComputer.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.connect.security.CustomUserDetailsService;

// 스프링 시큐리티를 이용해 로그인/로그아웃/인증/인가 등을 처리하기 위한 설정 파일입니다.
// @EnableWebSecurity가 붙어 있을 경우 스프링 시큐리티를 구성하는 기본적인 빈(Bean)들을 자동으로 구성해줍니다.
// WebSecurityConfigurerAdapter를 상속받으면,특정 메소드를 오버라이딩 함으로써 좀 더 손쉽게 설정할 수 있습니다.

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    // public void configure(WebSecurity web) 메소드를 오버라이딩 하는 이유s는
    // 인증/인가가 필요 없는 경로를 설정할 필요가 있기 때문입니다.

    // 해당객체의 ignoring()메소드에 무시될 경로를 지정하면 됩니다.
    // 여기에서는 "/webjars/**"를 설정하였습니다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    // WebSecurityConfigurerAdapter가 가지고 있는 void
    // configure(AuthenticationManagerBuilder auth)를 오버라이딩 하고 있습니다.
    // 해당 메소드를 오버라이딩 한 후 UserDetailsService인터페이스를 구현하고 있는 객체를
    // auth.userDetailsService()메소드의 인자로 전달하고
    // 있습니다.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/main", "/memembers/loginerror", "/members/joinform", "/members/join",
                        "/members/welcome")
                .permitAll().antMatchers("/securepage", "/members/**").hasRole("USER").anyRequest().authenticated()
                .and().formLogin().loginPage("/members/loginform").usernameParameter("userId")
                .passwordParameter("password").loginProcessingUrl("/authenticate")
                .failureForwardUrl("/members/loginerror?login_error=1").defaultSuccessUrl("/", true).permitAll().and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    // 패스워드 인코더를 빈으로 등록합니다. 암호를 인코딩하거나,
    // 인코딩된 암호와 사용자가 입력한 암호가 같은 지 확인할 때 사용합니다.
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
