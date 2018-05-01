package ro.ne8.blogsample.facades.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ne8.blogsample.dto.CommentDTO;
import ro.ne8.blogsample.entities.CommentEntity;
import ro.ne8.blogsample.facades.CommentFacade;
import ro.ne8.blogsample.services.CommentService;

import java.util.List;

@Service
public class CommentFacadeImpl implements CommentFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentFacadeImpl.class);


    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(final CommentDTO commentDTO) {
        LOGGER.debug("Saving comment entity");
        this.commentService.save(this.modelMapper.map(commentDTO, CommentEntity.class));
    }

    @Override
    public List<CommentDTO> findAllForPost(final Long postId) {
        return null;
    }

    @Override
    public void delete(final Long id) {
        this.commentService.deleteById(id);
    }

    @Override
    public void update(final CommentDTO commentDTO) {
        LOGGER.debug("Updating comment: " + commentDTO.getId());
        this.commentService.update(this.modelMapper.map(commentDTO, CommentEntity.class));
    }
}
