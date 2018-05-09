package ro.ne8.blogsample.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ne8.blogsample.entities.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    PostEntity findBySlugOrderByCreationTime(String slug);
}
