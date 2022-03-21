package hero.insta_clone.security.jwt;

import hero.insta_clone.domain.User;
import hero.insta_clone.service.CookieUtil;
import hero.insta_clone.service.JwtUtil;
import hero.insta_clone.service.MyUserDetailService;
import hero.insta_clone.service.RedisUtil;
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
        final Cookie jwtToken = cookieUtil.getCookie(httpServletRequest, JwtUtil.ACCESS_TOKEN_NAME);

        String email = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUname = null;

        try{
            if (jwtToken != null) {
                jwt = jwtToken.getValue();
                email = jwtUtil.getEmail(jwt);
            }
            if (email != null) {
                UserDetails userDetails = userDetailService.loadUserByUsername(email);

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException e) {
            Cookie refreshToken = cookieUtil.getCookie(httpServletRequest, JwtUtil.REFRESH_TOKEN_NAME);
            if (refreshToken != null) {
                refreshJwt = refreshToken.getValue();
            }
        } catch (Exception e ) {

        }

        try {
            if (refreshJwt != null) {
                refreshUname = redisUtil.getData(refreshJwt);

                if (refreshUname.equals(jwtUtil.getEmail(refreshJwt))) {
                    UserDetails userDetails = userDetailService.loadUserByUsername(refreshUname);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    User user = new User();
                    user.setEmail(refreshUname);
                    String newToken = jwtUtil.generateToken(user);

                    Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, newToken);
                    httpServletResponse.addCookie(newAccessToken);
                }
            }
        } catch (ExpiredJwtException e) {

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
