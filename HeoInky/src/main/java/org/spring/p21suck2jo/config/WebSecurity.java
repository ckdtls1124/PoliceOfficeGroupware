package org.spring.p21suck2jo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final UserDetailSecurity userDetailSecurity;
    private final AuthenticationFailureHandler customFailHandler;

    @Bean
    public SecurityFilterChain fileChain(HttpSecurity http) throws Exception{
        http.csrf().disable(); //페이지보안설정 Exception 예외처리
        http.userDetailsService(userDetailSecurity);

        //권한
        http.authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/police/**","/index","/event").authenticated()
                .antMatchers("/index").hasAnyRole("순경")
                .antMatchers("/admin/**").hasRole("ADMIN");

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
                .failureHandler(customFailHandler)
                .and()
                .oauth2Login()
                .loginPage("/login")
//                .userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
//                .userService(principalOauth2UserService)	//사용자정보를 처리할 때 사용한다
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
        return http.build();
    }
}
