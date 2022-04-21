package hero.insta_clone.controller;

import hero.insta_clone.domain.Profile;
import hero.insta_clone.domain.SecurityUser;
import hero.insta_clone.domain.User;
import hero.insta_clone.domain.response.Response;
import hero.insta_clone.dto.profile.ProfileDto;
import hero.insta_clone.dto.profile.ProfileUpdateDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
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
    @ResponseBody
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

    @GetMapping("/profile")
    @ResponseBody
    public List<Profile> mainProfile(@AuthenticationPrincipal SecurityUser User) {
        String email = User.getEmail();
        hero.insta_clone.domain.User loginUser = userRepository.findByEmail(email);

        List<Profile> findProfileList = profileRepository.findByUser_Id(loginUser.getId());

        return findProfileList;
    }

    @GetMapping("/profile/select")
    @ResponseBody
    public Profile selectProfile(@AuthenticationPrincipal SecurityUser user, @RequestParam int number) {
        String email = user.getEmail();
        hero.insta_clone.domain.User loginUser = userRepository.findByEmail(email);

        List<Profile> findProfileList = profileRepository.findByUser_Id(loginUser.getId());

        Iterator<Profile> iter = findProfileList.iterator();

        while (iter.hasNext()) {        //메인프로필 작업
            iter.next().setIsmain(false);
        }

        Profile profile = findProfileList.get(number);
        profile.setIsmain(true);
        profileRepository.save(profile);

        return profile;

    }

    @GetMapping("/profile/update/{profileId}")
    public String update(@PathVariable long profileId, Model model) {
        ProfileDto profileDto = profileService.getProfileDto(profileId);
        model.addAttribute(profileDto);
        return "profile/update";
    }

    @PostMapping("profile/update")
    @ResponseBody
    public Response updateProfile(ProfileUpdateDto profileUpdateDto, @AuthenticationPrincipal SecurityUser user, RedirectAttributes redirectAttributes) {
        profileService.update(profileUpdateDto);
        String email = user.getEmail();
        User loginUser = userRepository.findByEmail(email);

        redirectAttributes.addAttribute("id", loginUser.getId());

        return new Response("success", "프로필을 성공적으로 업데이트했습니다.", null);
    }
}
