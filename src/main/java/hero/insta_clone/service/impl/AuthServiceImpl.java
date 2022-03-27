package hero.insta_clone.service.impl;

import hero.insta_clone.domain.Salt;
import hero.insta_clone.domain.User;
import hero.insta_clone.repository.SaltRepository;
import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.service.authjwt.AuthService;
import hero.insta_clone.service.authjwt.SaltUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaltRepository saltRepository;

    @Autowired
    private SaltUtil saltUtil;

    @Override
    @Transactional
    public void signUpUser(User user) {
        String password = user.getPasswd();
        String salt = saltUtil.genSalt();
        log.info(salt);
        user.setSalt(new Salt(salt));
        user.setPasswd(saltUtil.encodePassword(salt, password));
        userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null) throw new Exception("멤버가 조회되지 않음");
        String salt = user.getSalt().getSalt();
        String s = saltUtil.encodePassword(salt, password);
        if(!user.getPasswd().equals(s))
            throw new Exception("비밀번호가 틀립니다.");
        return user;
    }
}
