package hero.insta_clone.controller;

import hero.insta_clone.domain.SecurityUser;
import hero.insta_clone.domain.User;
import hero.insta_clone.dto.post.PostUploadDto;
import hero.insta_clone.handler.ex.CustomValidationException;
import hero.insta_clone.repository.UserRepository;
import hero.insta_clone.security.jwt.JwtRequestFilter;
import hero.insta_clone.service.PostService;
import hero.insta_clone.service.authjwt.AuthService;
import hero.insta_clone.service.authjwt.CookieUtil;
import hero.insta_clone.service.authjwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private PostService postService;

    @GetMapping ("/post/upload")
    public String upload() {
        return "post/upload";
    }

    @PostMapping("/post")
    public String uploadPost(PostUploadDto postUploadDto, @RequestParam MultipartFile file, @AuthenticationPrincipal SecurityUser securityUser) {
        log.info("Request contains, File: " + file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.");
        }
        postService.save(postUploadDto, file, securityUser);

        return "Success";
    }
}
