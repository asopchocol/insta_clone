package hero.insta_clone.service;

import hero.insta_clone.domain.*;
import hero.insta_clone.dto.post.PostUploadDto;
import hero.insta_clone.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;

    @Value("${post.path}")
    private String uploadUrl;

    @Transactional
    public void save(PostUploadDto postUploadDto, MultipartFile multipartFile) {
        UUID uuid = UUID.randomUUID();
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename); //확장자 추출

        String imgFileName = uuid + "_" + originalFilename;

        //upload imgFile to LOCAL
        Path imgFilePath = Paths.get(uploadUrl + imgFileName);
        log.info("imgFileName : {}", imgFileName);
        log.info("imgFilePath : {}", imgFilePath);
        try {
            Files.write(imgFilePath, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mypath = uploadUrl + imgFileName;

        Post savedPost = postRepository.save(Post.builder()
                .text(postUploadDto.getText())
                .likesCount(0)
                .build());

        //postFile 객체 생성 & 설정 & 저장
        PostFile postFile = new PostFile();
        postFile.setFilename(imgFileName);
        postFile.setFilepath(mypath);
        postFile.setPost(savedPost);
        postFileRepository.save(postFile);
    }


}
