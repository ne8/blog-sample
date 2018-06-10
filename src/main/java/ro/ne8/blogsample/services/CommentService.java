package ro.ne8.blogsample.services;

import ro.ne8.blogsample.entities.CommentEntity;

import java.util.List;

public interface CommentService {

    void save(CommentEntity commentEntity);


    void delete(CommentEntity commentEntity);

    void update(CommentEntity commentEntity);

    List<CommentEntity> findAll();
}
