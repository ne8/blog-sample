package ro.ne8.blogsample.facades;

import ro.ne8.blogsample.dto.CommentDTO;

import java.util.List;

public interface CommentFacade {
    void save(CommentDTO commentDTO);

    List<CommentDTO> findAllForPost(Long postId);

    void delete(Long id);
}
