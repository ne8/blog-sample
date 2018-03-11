package ro.ne8.blogsample.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.ne8.blogsample.entities.CommentEntity;
import ro.ne8.blogsample.entities.UserEntity;
import ro.ne8.blogsample.repositories.CommentRepository;
import ro.ne8.blogsample.services.CommentService;
import ro.ne8.blogsample.services.PrincipalSupplier;
import ro.ne8.blogsample.services.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipalSupplier principalSupplier;

    @Override
    public void save(final CommentEntity commentEntity) {
        final UserDetails userDetails = this.principalSupplier.getSpringContextUserDetails();
        final UserEntity userEntity = this.userService.fetchCurrentUserEntity(userDetails.getUsername());
        commentEntity.setUserEntity(userEntity);
        this.commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentEntity> findAllForPost(final Long postId) {
        return null;
    }

    @Override
    public void deleteById(final Long id) {
        this.commentRepository.deleteById(id);
    }
}
