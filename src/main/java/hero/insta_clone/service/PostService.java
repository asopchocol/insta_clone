package hero.insta_clone.service;

import hero.insta_clone.domain.User;
import hero.insta_clone.dto.post.PostUploadDto;
import hero.insta_clone.repository.CommentRepository;
import hero.insta_clone.repository.LikesRepository;
import hero.insta_clone.repository.PostRepository;
import hero.insta_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;
    private final EntityManager em;

    @Transactional
    public void save(PostUploadDto postUploadDto, MultipartFile multipartFile, User user) {
        UUID uuid = UUID.randomUUID(); //랜덤 uuid생성
        String imgFileName = uuid + "_" + multipartFile.getOriginalFilename();

    }


}
