package hero.insta_clone.service;

import hero.insta_clone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;

}
