package hero.insta_clone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
    @Id
    @Column(name = "tag_id")
    private Long id;
}
