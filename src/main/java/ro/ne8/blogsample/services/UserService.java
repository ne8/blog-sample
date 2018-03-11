package ro.ne8.blogsample.services;

import ro.ne8.blogsample.entities.UserEntity;

public interface UserService {

    void save(UserEntity userEntity);


    UserEntity fetchCurrentUserEntity(String username);
}
