package team.login.teamproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final UserDetailSecurity userDetailSecurity;
    @Bean
    public SecurityFilterChain fileChain(HttpSecurity http) throws Exception{
        http.csrf().disable(); //페이지보안설정 Exception 예외처리
        http.userDetailsService(userDetailSecurity);

        //권한
        http.authorizeHttpRequests()
                .antMatchers("/","/login","/join","/IdSelect","/PwSelect").permitAll()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN");

        http.formLogin()
                .loginPage("/Login")
                .loginProcessingUrl("login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("logout"))
                .logoutSuccessUrl("/");
        return http.build();
    }
}
