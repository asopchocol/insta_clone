package hero.insta_clone.repository;

import hero.insta_clone.domain.Profile;
import hero.insta_clone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByNickname(String nickname);
    List<Profile> findByUser_Id(long userId);
    Profile findByIsmain(boolean flag);
    @Query(value = "SELECT * FROM Profile as p WHERE p.user_id = ?1 and p.ismain = ?2", nativeQuery = true)
    Profile findProfileByUser_IdAndIsmainNative(Long user_id, Boolean ismain);
}