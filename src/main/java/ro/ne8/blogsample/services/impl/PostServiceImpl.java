package ro.ne8.blogsample.services.impl;

import org.springframework.stereotype.Service;
import ro.ne8.blogsample.entities.PostEntity;
import ro.ne8.blogsample.services.PostService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Override
    public void save(final PostEntity postEntity) {

    }

    @Override
    public List<PostEntity> findAll() {
        return null;
    }

    @Override
    public PostEntity getBySlug(final String slug) {
        return null;
    }

    @Override
    public void delete(final PostEntity postEntity) {

    }

    @Override
    public List<PostEntity> findByUsername(final String username) {
        return null;
    }
}
