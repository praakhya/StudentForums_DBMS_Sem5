package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.UserMapper;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.pojo.User;
import com.projects.pes.forumbackend.repositories.UserRepository;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public Iterable<User> getUsers() {
        List<User>  userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(
                e->userList.add(userMapper.convert(e))
        );
        return userList;
    }
    public User getUser(String username) {
        return userMapper.convert(userRepository
                .findByUsername(username)
                .orElseThrow(() -> {
                    throw new UserDoesntExist(username);
                })
        );
    }
    public User save(User user) {
        return userMapper.convert(
                userRepository.save(userMapper.convert(user))
                );
    }
    public User delete(String username) {
        Optional<UserEntity> optionalUserEntity=userRepository.findByUsername(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = optionalUserEntity.get();
            userRepository.delete(entity);
            return userMapper.convert(entity);
        }
        throw new UserDoesntExist(username);
    }
    public ProfileImage updateProfileImage(String username,
                                           byte[] bytes,
                                           String mimeType) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = optionalUserEntity.get();
            PictureEntity picture = new PictureEntity(bytes, mimeType);
            entity.setPicture(picture);
            entity = userRepository.save(entity);
            userMapper.convert(entity);
            return new ProfileImage(username,
                    Constants.Paths.FACULTY_PATH+Constants.Paths.IMAGE_UPLOAD_PATH.replace("{username}", username),
                    entity.getPicture().getMimeType(),
                    null);
        }
        throw new UserDoesntExist(username);
    }
    public ProfileImage getImage(String username) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = optionalUserEntity.get();
            return new ProfileImage(username,
                    Constants.Paths.IMAGE_UPLOAD_PATH.replace("{username}", username),
                    entity.getPicture().getMimeType(),
                    entity.getPicture().getImageData());
        }
        throw new UserDoesntExist(username);
    }
}