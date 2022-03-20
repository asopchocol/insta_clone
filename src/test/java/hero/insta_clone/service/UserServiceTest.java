package hero.insta_clone.service;

import hero.insta_clone.domain.User;
import hero.insta_clone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    void 회원가입() {
        //given
        User user = new User("asopchocol@naver.com", "1234");

        //when
        Long saveId = userService.join(user);

        //then
        User findUser = userService.findOne(saveId).get();
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

}