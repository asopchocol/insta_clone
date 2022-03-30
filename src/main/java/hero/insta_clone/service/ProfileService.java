package hero.insta_clone.service;

import hero.insta_clone.domain.Profile;
import hero.insta_clone.dto.profile.ProfileUpdateDto;
import hero.insta_clone.repository.ProfileRepository;
import hero.insta_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public void update(ProfileUpdateDto profileUpdateDto) {
        Profile profile = profileRepository.findByNickname(profileUpdateDto.getNickname());
        profile.update(profileUpdateDto.getName(), profileUpdateDto.getDescription());
    }



}
