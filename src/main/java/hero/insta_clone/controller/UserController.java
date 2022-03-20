package hero.insta_clone.controller;

import hero.insta_clone.domain.User;
import hero.insta_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("user")
    public List<User> findAllMember() {
        return userRepository.findAll();
    }
}
