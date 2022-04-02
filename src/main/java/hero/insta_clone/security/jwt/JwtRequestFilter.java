package hero.insta_clone.security.jwt;

import hero.insta_clone.domain.User;
import hero.insta_clone.service.authjwt.CookieUtil;
import hero.insta_clone.service.authjwt.JwtUtil;
import hero.insta_clone.service.authjwt.MyUserDetailService;
import hero.insta_clone.service.authjwt.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtRequestFilter doFilterInternal 실행");
        final Cookie jwtToken = cookieUtil.getCookie(httpServletRequest, JwtUtil.ACCESS_TOKEN_NAME);
        log.info("jwtToken's value = {}", jwtToken);

        String email = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUname = null;

        try{
            if (jwtToken != null) {
                jwt = jwtToken.getValue();
                email = jwtUtil.getEmail(jwt);
                log.info("jwt's value = {}", jwt);
                log.info("email = {}", email);
            }
            if (email != null) {
                if (redisUtil.getData(jwt) != null) {
                    log.warn("this token already logout!");
                }
                else {
                    UserDetails userDetails = userDetailService.loadUserByUsername(email);
                    log.info("userDetails = {}", userDetails);

                    if (jwtUtil.validateToken(jwt, userDetails)) {
                        log.info("validate success");
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        log.info("usernamePasswordAuthenticationToken = {}", usernamePasswordAuthenticationToken);
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        } catch (ExpiredJwtException e) {
            log.info("ExpiredJwtExption이 발생하였습니다.");
            Cookie refreshToken = cookieUtil.getCookie(httpServletRequest, JwtUtil.REFRESH_TOKEN_NAME);
            log.info("refreshToken = {}", refreshToken);
            if (refreshToken != null) {
                refreshJwt = refreshToken.getValue();
                log.info("refreshToken이 있습니다. 값을 꺼냅니다. refreshJwt = {}", refreshJwt);
            }
        } catch (Exception e ) {

        }

        try {
            if (refreshJwt != null) {
                refreshUname = redisUtil.getData(refreshJwt);
                log.info("refreshUname = {} ", refreshUname);

                if (refreshUname.equals(jwtUtil.getEmail(refreshJwt))) {
                    UserDetails userDetails = userDetailService.loadUserByUsername(refreshUname);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    User user = new User();
                    user.setEmail(refreshUname);
                    String newToken = jwtUtil.generateToken(user);
                    log.info("RefreshToken -> newToken 생성 알림 : {}", newToken);

                    Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, newToken);
                    httpServletResponse.addCookie(newAccessToken);
                }
            }
        } catch (ExpiredJwtException e) {

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}