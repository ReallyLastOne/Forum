package com.forum.config;

import com.forum.model.*;
import com.forum.model.htmlforms.MessageContent;
import com.forum.model.htmlforms.MessageForm;
import com.forum.model.htmlforms.PasswordForm;
import com.forum.model.htmlforms.WarnReasonForm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("userInfo")
    public UserInfo userInfo() {
        return new UserInfo();
    }

    @ModelAttribute("passwordForm")
    public PasswordForm passwordForm() {
        return new PasswordForm();
    }

    @ModelAttribute("messageContent")
    public MessageContent messageContent() {
        return new MessageContent();
    }

    @ModelAttribute("message")
    public MessageForm messageForm() {
        return new MessageForm();
    }

    @ModelAttribute("warnReasonForm")
    public WarnReasonForm warnReasonForm() {
        return new WarnReasonForm();
    }
}
