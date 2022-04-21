package hero.insta_clone.service;

import hero.insta_clone.domain.Profile;
import hero.insta_clone.domain.SecurityUser;
import hero.insta_clone.domain.User;
import hero.insta_clone.dto.profile.ProfileDto;
import hero.insta_clone.dto.profile.ProfileUpdateDto;
import hero.insta_clone.repository.ProfileRepository;
import hero.insta_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional
    public void update(ProfileUpdateDto profileUpdateDto) {
        Profile profile = profileRepository.findByNickname(profileUpdateDto.getNickname());
        profile.update(profileUpdateDto.getName(), profileUpdateDto.getDescription());
    }

    public void signUpProfile(Profile profile, User user) {
        profile.setUser(user);
        profileRepository.save(profile);
    }

    @Transactional
    public void changeIsMain(ProfileUpdateDto profileUpdateDto) {
        Profile profile = profileRepository.findById(profileUpdateDto.getId()).get();
        profile.setIsmain(true);
    }

    public ProfileDto getProfileDto(long profileId) {
        Profile profile = profileRepository.findById(profileId).get();

        ProfileDto profileDto = ProfileDto.builder()
                .id(profileId)
                .name(profile.getName())
                .nickname(profile.getNickname())
                .description(profile.getDescription())
                .profileImgUrl(profile.getProfileImgUrl())
                .build();
        return profileDto;
    }
}
