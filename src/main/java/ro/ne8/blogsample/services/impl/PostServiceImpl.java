package ro.ne8.blogsample.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.ne8.blogsample.entities.PostEntity;
import ro.ne8.blogsample.entities.UserEntity;
import ro.ne8.blogsample.repositories.PostRepository;
import ro.ne8.blogsample.services.PostService;
import ro.ne8.blogsample.services.PrincipalSupplier;
import ro.ne8.blogsample.services.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipalSupplier principalSupplier;

    @Override
    public void save(final PostEntity postEntity) {
        final UserDetails userDetails = this.principalSupplier.getSpringContextUserDetails();
        final UserEntity userEntity = this.userService.fetchCurrentUserEntity(userDetails.getUsername());
        postEntity.setUserEntity(userEntity);
        postEntity.setCreationTime(new Date());
        this.postRepository.save(postEntity);
    }

    @Override
    public List<PostEntity> findAll() {
        final List<PostEntity> postEntities = new ArrayList<>();
        final Iterable<PostEntity> postEntityIterable = this.postRepository.findAll();
        postEntityIterable.forEach(postEntities::add);
        return postEntities;
    }

    @Override
    public PostEntity findBySlug(final String slug) {
        return this.postRepository.findBySlugOrderByCreationTime(slug);
    }

    @Override
    public void delete(final PostEntity postEntity) {
        this.postRepository.delete(postEntity.getId());
    }

    @Override
    public List<PostEntity> findByUsername(final String username) {
        return null;
    }

    @Override
    public PostEntity findById(final Long id) {
        return this.postRepository.findOne(id);
    }

    @Override
    public void update(final PostEntity postEntity) {
        final PostEntity postEntityToBeUpdated = this.postRepository.findOne(postEntity.getId());
        postEntityToBeUpdated.setTextContent(postEntity.getTextContent());
        postEntityToBeUpdated.setTitle(postEntity.getTitle());
        postEntityToBeUpdated.setSlug(postEntity.getSlug());
        postEntityToBeUpdated.setUpdateTime(new Date());
        this.postRepository.save(postEntityToBeUpdated);
    }
}
