package hero.insta_clone.domain;

import javax.persistence.*;

@Entity
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String file;
    private String file_type;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
