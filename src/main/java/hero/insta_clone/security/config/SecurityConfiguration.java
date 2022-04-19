package hero.insta_clone.security.config;

import hero.insta_clone.domain.response.Response;
import hero.insta_clone.security.jwt.JwtRequestFilter;
import hero.insta_clone.service.authjwt.CookieUtil;
import hero.insta_clone.service.authjwt.JwtUtil;
import hero.insta_clone.service.authjwt.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        try {
                            log.info("logout 실행");
                            Cookie accessToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
                            Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);

                            String accessTokenValue = accessToken.getValue();
                            String refreshTokenValue = refreshToken.getValue();

                            String email = jwtUtil.getEmail(accessTokenValue);

                            redisUtil.setDataExpire(accessTokenValue, email, jwtUtil.getRemainMilliSeconds(accessTokenValue));
                            redisUtil.deleteData(refreshTokenValue);

                            Cookie deleteAccessToken = cookieUtil.deleteCookie(JwtUtil.ACCESS_TOKEN_NAME, accessTokenValue);
                            Cookie deleteRefreshToken = cookieUtil.deleteCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshTokenValue);

                            response.addCookie(deleteAccessToken);
                            response.addCookie(deleteRefreshToken);

                            log.info("logout 성공");

                        } catch (Exception e) {
                            log.info("logout 실패");
                        }
                    }
                })
                .and()
                .httpBasic()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}