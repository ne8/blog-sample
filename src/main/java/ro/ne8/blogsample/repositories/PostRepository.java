package ro.ne8.blogsample.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ne8.blogsample.entities.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    PostEntity findBySlugOrderByCreationTimeAsc(String slug);

    List<PostEntity> findByUserEntity_UsernameOrderByCreationTimeDesc(String username);

    void deleteAllBySlug(String slug);
}
