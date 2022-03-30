package hero.insta_clone.dto.post;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PostDto {
    private long id;
    private String text;
    private String postImgUrl;
}
