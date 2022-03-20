package hero.insta_clone.domain;

import hero.insta_clone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = new User("asopchocol@gmail.com", "1234");

        User saveId = userRepository.save(user);

        assertThat(saveId.getId()).isEqualTo(user.getId());
    }

    @Test
    public void findAll() {
        User user1 = new User("asopchocol@naver.com", "1234");
        User user2 = new User("asopchocol@hanmail.com", "1234");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(2);

    }
}
