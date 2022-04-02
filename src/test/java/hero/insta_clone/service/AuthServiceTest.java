package hero.insta_clone.service;

import hero.insta_clone.domain.User;
import hero.insta_clone.domain.request.RequestLoginUser;
import hero.insta_clone.service.authjwt.AuthService;
import hero.insta_clone.service.authjwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void signUp() {
        User user = new User("asopchocol@hotmail.com","1234");
        authService.signUpUser(user);
    }

    @Test
    public void login() {
        RequestLoginUser loginUser = new RequestLoginUser("asopchocol@hotmail.com", "1234");
        try {
            authService.loginUser(loginUser.getEmail(), loginUser.getPassword());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}