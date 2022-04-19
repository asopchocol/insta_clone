package hero.insta_clone.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostFile> postFileList = new ArrayList<>();

    @OrderBy("id")
    @JsonIgnoreProperties({"post"}) //무한참조 막기위함.
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<Likes> likesList;

    @JsonIgnoreProperties({"profileList"})
    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;

    @OneToMany
    @JoinTable(name = "PostTag",
                joinColumns = @JoinColumn(name="post_id"),
                inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private List<Tag> tag = new ArrayList<Tag>();


    @Transient
    private long likesCount;

    @Transient
    private boolean likesState;

    public void updateLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public void updateLikesState(boolean likesState) {
        this.likesState = likesState;
    }

    public void makePost(Long id) {
        this.id = id;
    }

    @Builder
    public Post(String text, Profile profile, long likesCount) {
        this.text = text;
        this.profile = profile;
        this.likesCount = likesCount;
    }

}
