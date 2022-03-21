package hero.insta_clone.controller;

import hero.insta_clone.domain.User;
import hero.insta_clone.domain.request.RequestLoginUser;
import hero.insta_clone.domain.response.Response;
import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.service.AuthService;
import hero.insta_clone.service.CookieUtil;
import hero.insta_clone.service.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("/signup")
    public Response signUpUser(@RequestBody User user) {
        try {
            log.info("User.email={}, User.passwd={}", user.getEmail(), user.getPasswd());
            authService.signUpUser(user);
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", null);
        } catch (Exception e) {
            return new Response("error", "회원가입을 하는 도중 오류가 발생했습니다.", null);
        }
    }

    @PostMapping("login")
    public Response login(@RequestBody User loginUser, HttpServletRequest request, HttpServletResponse response) {
        try{
            final User user = authService.loginUser(loginUser.getEmail(), loginUser.getPassword());
            final String token = jwtUtil.generateToken(user);
            final String refreshJwt = jwtUtil.generateRefreshToken(user);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            response.addCookie(accessToken);
            response.addCookie(refreshToken);
            return new Response("success", "로그인에 성공했습니다.", token);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
