package hero.insta_clone.repository;

import hero.insta_clone.domain.Salt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaltRepository extends JpaRepository<Salt, Long> {
}
