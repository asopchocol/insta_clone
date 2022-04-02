package hero.insta_clone.controller;

import hero.insta_clone.domain.User;
import hero.insta_clone.domain.response.Response;
import hero.insta_clone.repository.ProfileRepository;
import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.service.authjwt.AuthService;
import hero.insta_clone.service.authjwt.CookieUtil;
import hero.insta_clone.service.authjwt.JwtUtil;
import hero.insta_clone.service.authjwt.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("")
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private RedisUtil redisUtil;

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

    @PostMapping("/login")
    public Response login(@RequestBody User loginUser, HttpServletRequest request, HttpServletResponse response) {
        try{
            log.info("login 진입 실행");
            final User user = authService.loginUser(loginUser.getEmail(), loginUser.getPassword());
            final String token = jwtUtil.generateToken(user);
            final String refreshJwt = jwtUtil.generateRefreshToken(user);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setDataExpire(refreshJwt, user.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            response.addCookie(accessToken);
            response.addCookie(refreshToken);

            return new Response("success", "로그인에 성공했습니다.", token);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }

    @GetMapping("/logout")
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
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

            return new Response("success", "로그아웃에 성공했습니다.", accessTokenValue);
        } catch (Exception e) {
            return new Response("error", "로그아웃에 실패했습니다.", e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{userId}")
    public Response deleteId(@PathVariable long userId) {
        if (userRepository.findById(userId) != null) {
            userRepository.deleteById(userId);
            return new Response("success", "유저삭제에 성공했습니다.", null);
        }
        else {
            return new Response("error", "유저삭제에 실패했습니다.", null);
        }
    }
}