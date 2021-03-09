package kr.or.connect.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.connect.ApplicationConfig;
import kr.or.connect.config.SecurityConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, SecurityConfig.class })
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncoder() throws Exception {
        System.out.println(passwordEncoder.encode("1234"));
    }

    @Test
    public void passwordTest() throws Exception {
        String encodedPassword = "$2a$10$USbExG2YOZJqu5rR9eWAqO3NqwjS6c8uI0c695cnURA2gxqRnx41O";
        String password = "1234";
        boolean test = passwordEncoder.matches(password, encodedPassword);
        System.out.println(test);
    }

}
