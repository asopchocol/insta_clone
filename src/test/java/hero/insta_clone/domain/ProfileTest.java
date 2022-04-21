package hero.insta_clone.domain;

import hero.insta_clone.repository.ProfileRepository;
import hero.insta_clone.service.ProfileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileTest {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    @Transactional
    void update() {
        Profile profile = profileRepository.findByNickname("biber");
        profile.setIsmain(true);

        Profile IsmainProfile = profileRepository.findByIsmain(true);

        Assertions.assertThat(profile.getIsmain()).isEqualTo(IsmainProfile.getIsmain());
    }


}