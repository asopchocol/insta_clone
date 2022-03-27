package hero.insta_clone.controller;

import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.security.jwt.JwtRequestFilter;
import hero.insta_clone.service.authjwt.AuthService;
import hero.insta_clone.service.authjwt.CookieUtil;
import hero.insta_clone.service.authjwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CookieUtil cookieUtil;

    @GetMapping("/post")
    public String post(HttpServletRequest request, HttpServletResponse response) {

        return "ok";
    }

}
