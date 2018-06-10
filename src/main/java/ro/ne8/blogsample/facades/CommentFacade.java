package ro.ne8.blogsample.facades;

import ro.ne8.blogsample.dto.CommentDTO;

import java.util.List;

public interface CommentFacade {
    void save(CommentDTO commentDTO);


    List<CommentDTO> findAll();

    void delete(CommentDTO commentDTO);

    void update(CommentDTO commentDTO);
}
