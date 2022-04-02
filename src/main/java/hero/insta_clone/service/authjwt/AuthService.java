package hero.insta_clone.service.authjwt;

import hero.insta_clone.domain.User;

public interface AuthService {
    void signUpUser(User user);

    User loginUser(String email, String password) throws Exception;

    void logout(String accessToken, String refreshToken);
}
