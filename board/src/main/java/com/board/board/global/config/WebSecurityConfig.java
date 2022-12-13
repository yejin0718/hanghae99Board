package com.board.board.global.config;


import com.board.board.global.jwt.JwtAuthFilter;
import com.board.board.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor //자동 주입
@Configuration //Bean등록
@EnableWebSecurity //스프링 Security 지원 가능케함
@EnableGlobalMethodSecurity(securedEnabled = true) //@Secured 활성화
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } //암호화 객체 반환

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console()) //he-console 주소 허요
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //static쪽 resources 허용
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //session 정책을 Stateless로

        http.authorizeRequests().antMatchers("/member/**").permitAll() //유저 관련 url 허용
                .antMatchers("/post/**").permitAll() //게시물 관련 url 모두 허용
//                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                // JWT 인증/인가를 사용하기 위한 설정
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
                //위 코드의 경우 로그인을 확인하는 UsernamePasswordAuthenticationFilter 이전에 JwtAuthFilter를 먼저 하겠다.


        http.formLogin().loginPage("/member/login").permitAll(); //로그인이 안돼서 인증이 안되었을 경우 "/member/login-page" 이페이지로 리턴

        http.exceptionHandling().accessDeniedPage("/member/forbidden");

        return http.build();
    }
}
