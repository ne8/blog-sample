package ro.ne8.blogsample.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ne8.blogsample.entities.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    void deleteById(Long id);
}
