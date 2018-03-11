package ro.ne8.blogsample.facades;

import ro.ne8.blogsample.dto.PostDTO;

import java.util.List;

public interface PostFacade {

    void save(PostDTO postDTO);

    List<PostDTO> findAll();

    PostDTO getBySlug(String slug);

    void delete(PostDTO postDTO);

    List<PostDTO> findByUsername(String username);
}
