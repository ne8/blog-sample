package ro.ne8.blogsample.facades.impl;

import org.springframework.stereotype.Service;
import ro.ne8.blogsample.dto.PostDTO;
import ro.ne8.blogsample.facades.PostFacade;

import java.util.List;

@Service
public class PostFacadeImpl implements PostFacade {
    @Override
    public void save(final PostDTO postDTO) {

    }

    @Override
    public List<PostDTO> findAll() {
        return null;
    }

    @Override
    public PostDTO getBySlug(final String slug) {
        return null;
    }

    @Override
    public void delete(final PostDTO postDTO) {

    }

    @Override
    public List<PostDTO> findByUsername(final String username) {
        return null;
    }
}
