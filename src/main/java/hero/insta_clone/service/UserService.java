package hero.insta_clone.service;

import hero.insta_clone.domain.User;
import hero.insta_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    /*회원가입*/
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }

    /*전체 회원 조회*/
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long memberId) {
        return userRepository.findById(memberId);
    }

}
