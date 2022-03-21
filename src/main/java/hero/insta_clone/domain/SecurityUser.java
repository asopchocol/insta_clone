package hero.insta_clone.domain;

import org.springframework.security.core.authority.AuthorityUtils;

import java.time.LocalDateTime;
import java.util.List;

public class SecurityUser extends User {
    private static final long serialVersionUid = 1L;

    public SecurityUser(User user) {
        super(user.getEmail(),"{noop}"+ user.getPasswd());
    }
}
