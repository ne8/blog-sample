package ro.ne8.blogsample.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ne8.blogsample.entities.UserEntity;
import ro.ne8.blogsample.repositories.UserRepository;
import ro.ne8.blogsample.services.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(final UserEntity userEntity) {
        this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity fetchCurrentUserEntity(final String username) {
        UserEntity userEntity = new UserEntity();
        if (!this.userRepository.existsByUsername(username)) {
            userEntity.setUsername(username);
            this.save(userEntity);
        } else {
            userEntity = this.userRepository.findByUsername(username);
        }
        return userEntity;
    }
}
