package hero.insta_clone.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
