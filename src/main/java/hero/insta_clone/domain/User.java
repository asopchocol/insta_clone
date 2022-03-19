package hero.insta_clone.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name="user")
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String passwd;

    public User(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }
}
