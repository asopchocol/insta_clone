package hero.insta_clone.dto.profile;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class ProfileUpdateDto {
    private Long id;
    private String name;
    private String nickname;
    private String description;
    private String profileImgUrl;
}
