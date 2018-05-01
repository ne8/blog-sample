package ro.ne8.blogsample.services;

import ro.ne8.blogsample.entities.CommentEntity;

import java.util.List;

public interface CommentService {

    void save(CommentEntity commentEntity);

    List<CommentEntity> findAllForPost(Long postId);

    void deleteById(Long id);

    void update(CommentEntity commentEntity);
}
