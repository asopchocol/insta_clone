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

}
