package ro.ne8.blogsample.facades.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ne8.blogsample.dto.PostDTO;
import ro.ne8.blogsample.entities.PostEntity;
import ro.ne8.blogsample.facades.PostFacade;
import ro.ne8.blogsample.services.PostService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostFacadeImpl implements PostFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostFacadeImpl.class);

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(final PostDTO postDTO) {
        LOGGER.debug("Saving post entity");
        this.postService.save(this.modelMapper.map(postDTO, PostEntity.class));
    }

    @Override
    public List<PostDTO> findAll() {
        final List<PostEntity> postEntities = this.postService.findAll();
        final List<PostDTO> postDTOS = new ArrayList<>();
        for (final PostEntity postEntity : postEntities) {
            postDTOS.add(this.modelMapper.map(postEntity, PostDTO.class));
        }
        return postDTOS;
    }

    @Override
    public PostDTO findBySlug(final String slug) {
        return this.modelMapper.map(this.postService.findBySlug(slug), PostDTO.class);
    }

    @Override
    public void delete(final PostDTO postDTO) {
        this.postService.delete(this.modelMapper.map(postDTO, PostEntity.class));
    }

    @Override
    public List<PostDTO> findByUsername(final String username) {
        final List<PostEntity> postEntityList = this.postService.findByUsername(username);
        final List<PostDTO> postDTOList = new ArrayList<>();
        postEntityList.forEach(postEntity -> postDTOList.add(this.modelMapper.map(postEntity, PostDTO.class)));
        return postDTOList;
    }

    @Override
    public void update(final PostDTO postDTO) {
        LOGGER.debug("Updating post: " + postDTO.getId());
        this.postService.update(this.modelMapper.map(postDTO, PostEntity.class));
    }

    @Override
    public void deleteAllBySlug(final String slug) {
        this.postService.deleteAllBySlug(slug);
    }
}
