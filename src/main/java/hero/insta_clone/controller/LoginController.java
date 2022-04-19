package hero.insta_clone.controller;

import hero.insta_clone.domain.User;
import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.service.LoginService;
import hero.insta_clone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Controller
public class LoginController {

//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private LoginService loginService;
//
//    /**
//     * 유저 페이지
//     * @return
//     */
//    @GetMapping("/user")
//    public String user() {
//        return "user";
//    }
//
//    /**
//     * 로그인 폼 페이지
//     * @return
//     */
//    @GetMapping("/loginForm")
//    public String login() {
//        return "loginForm";
//    }
//
//    @PostMapping("/login")
//    public String loginForm(UserForm userForm, HttpSession session) {
//        User findUser = userRepository.findByEmail(userForm.getEmail());
//
//        if (loginService.login(findUser)) { //로그인 성공시
//            session.setAttribute("id", findUser);
//            return "/login/main";
//        }
//        return "loginForm";
//    }
//
//    /**
//     * 회원가입 페이지
//     *
//     * @return
//     */
//    @GetMapping("/joinForm")
//    public String joinForm() {
//        return "joinForm";
//    }
//
//    /**
//     * 회원 가입이 실행되는 부분
//     * @param userForm
//     * @return
//     */
//    @PostMapping("/join")
//    public String loginId(UserForm userForm) {
//        User member = new User(userForm.getEmail(), userForm.getPasswd());
//        userService.join(member);
//        return "redirect:/loginForm";
//    }
//
//    /**
//     * 메인페이지 로그아웃
//     */
//    @RequestMapping(value="logout2", method= RequestMethod.GET)
//    public String logoutMainGET(HttpServletRequest request) throws Exception {
//        log.info("LogoutMainGet메서드 진입");
//        HttpSession session = request.getSession();
//
//        session.invalidate();
//
//        return "redirect:/";
//    }
}
