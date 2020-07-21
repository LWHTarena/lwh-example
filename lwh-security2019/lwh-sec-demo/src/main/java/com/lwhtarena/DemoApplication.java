package com.lwhtarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liwh
 * @Title: DemoApplication
 * @Package com.lwhtarena
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:23
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

}
