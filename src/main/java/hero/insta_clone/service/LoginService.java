package hero.insta_clone.service;

import hero.insta_clone.domain.User;
import hero.insta_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private final UserRepository userRepository;

    public boolean login(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());

        if (findUser == null) {
            return false;
        }
        if (!findUser.getPasswd().equals(user.getPasswd())) {
            return false;
        }
        return true;
    }

}
