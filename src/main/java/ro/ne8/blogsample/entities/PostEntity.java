package ro.ne8.blogsample.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts", schema = "blog")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "text_content")
    private String textContent;


    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "creation_date")
    private Date creationTime;

    @Column(name = "update_date")
    private Date updateTime;

    @OneToMany(mappedBy = "postEntity")
    private List<CommentEntity> commentEntities;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTextContent() {
        return this.textContent;
    }

    public void setTextContent(final String textContent) {
        this.textContent = textContent;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(final Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<CommentEntity> getCommentEntities() {
        return this.commentEntities;
    }

    public void setCommentEntities(final List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(final String slug) {
        this.slug = slug;
    }
}
