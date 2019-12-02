package me.ghsong.board.config;

import lombok.AllArgsConstructor;
import me.ghsong.board.config.security.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-18
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LoginService loginService;

    /**
     * Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security 설정
     * (WebSecurity - FilterChainProxy를 생성하는 필터)
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**");
    }

    /**
     * HTTP 요청에 대한 웹 기반 보안을 구성
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/boards", "/boards/lists", "/console/**", "/**").permitAll()        // 모든권한 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN")                                 // ADMIN 롤을 가진 사용자 접근가능
                .antMatchers("/members/**").hasAnyRole("MEMBER", "ADMIN")          // ADMIN, MEMBER 롤 중 하나라도 가진 사용자 접근가능
                .antMatchers("/boards/**").hasAnyRole("MEMBER", "ADMIN")
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/login")                                                                    // 로그인 뷰 페이지 URL
                .loginProcessingUrl("/login")                                                           // 로그인 POST 처리 URL
                .defaultSuccessUrl("/boards")                                                          // 로그인이 성공했을 때 이동되는 페이지
                .failureUrl("/login-error")                                                             // 로그인 실패 시 이동할 페이지 URL
                .usernameParameter("memberId")                                                          // 로그인 form에서 아이디는 name=username인 input을 기본, 파라미터 변경 가능
                .passwordParameter("memberPassword")
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))                    // 로그아웃 URL
                .logoutSuccessUrl("/login")                                                             // 로그아웃 성공했을 때 이동되는 페이지
                .invalidateHttpSession(true)                                                            // HTTP 세션을 초기화하는 작업
            .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/denied");                                        // 권한이 없을 때 이동되는 페이지

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         Spring Security에서 모든 인증은 AuthenticationManager를 통해 이루어지며
         AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용
         */
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

}
