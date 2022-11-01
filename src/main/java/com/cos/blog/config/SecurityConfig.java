package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //IoC관리 빈등록
@EnableWebSecurity //security 필터 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
//특정 주소를 접근하면 권한인증 체크
public class SecurityConfig{

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //IoC 리턴값을 스프링이 관리하게된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }




    //시큐리티가 대신 로그인해주는데 password를 가로채기하는데
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화해서 DB에있는 해쉬랑 비교할수 있음



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf토큰 비활성화
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                //스프링스큐리티가 해당주소로 요청오는 로그인을 대신 로그인해줌
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/"); //정상적으로되면 메인화면으로 감
        return http.build();
    }
}
