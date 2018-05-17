package ro.ne8.blogsample.services;

import ro.ne8.blogsample.entities.PostEntity;

import java.util.List;

public interface PostService {

    void save(PostEntity postEntity);

    List<PostEntity> findAll();

    PostEntity findBySlug(String slug);

    void delete(PostEntity postEntity);

    List<PostEntity> findByUsername(String username);

    PostEntity findById(Long id);

    void update(PostEntity postEntity);

    void deleteAllBySlug(String slug);
}
