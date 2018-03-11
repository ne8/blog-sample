package ro.ne8.blogsample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", schema = "blog")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;


    @OneToMany(mappedBy = "userEntity")
    private List<PostEntity> postEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<CommentEntity> commentEntities;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
