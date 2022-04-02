package hero.insta_clone.controller;

import hero.insta_clone.domain.Profile;
import hero.insta_clone.domain.User;
import hero.insta_clone.domain.response.Response;
import hero.insta_clone.repository.ProfileRepository;
import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.service.ProfileService;
import hero.insta_clone.service.authjwt.AuthService;
import hero.insta_clone.service.authjwt.CookieUtil;
import hero.insta_clone.service.authjwt.JwtUtil;
import hero.insta_clone.service.authjwt.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

@Slf4j
@RestController
public class ProfileController {
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
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile/signup")
    public Response signUpProfile(@RequestBody Profile profile, HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie accessToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
            String jwt = accessToken.getValue();
            String email = jwtUtil.getEmail(jwt);
            User loginUser = userRepository.findByEmail(email);
            profileService.signUpProfile(profile, loginUser);

            return new Response("success", "프로필을 성공적으로 만들었습니다..", null);
        } catch (Exception e) {
            return new Response("error", "프로필을 만드는 도중 오류가 발생했습니다.", null);
        }
    }
}
