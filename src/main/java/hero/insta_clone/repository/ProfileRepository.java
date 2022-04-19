package hero.insta_clone.repository;

import hero.insta_clone.domain.Profile;
import hero.insta_clone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByNickname(String nickname);
    List<Profile> findByUser_Id(long userId);
}
