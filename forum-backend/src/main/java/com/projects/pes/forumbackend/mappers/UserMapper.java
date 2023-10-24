package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.pojo.Post;
import com.projects.pes.forumbackend.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setPicture(new PictureEntity(user.getImageData(), user.getMimeType()));
        userEntity.setContact(user.getContact());
        userEntity.setForums(user.getForums());
        return userEntity;
    }
    public  User convert(UserEntity userEntity) {
        PictureEntity pictureEntity = userEntity.getPicture();
        byte[] imageData = null;
        String mimeType = null;
        if (pictureEntity != null) {
            imageData = pictureEntity.getImageData();
            mimeType = pictureEntity.getMimeType();
        }
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPassword(),
                imageData,
                mimeType,
                userEntity.getContact(),
                userEntity.getForums()
        );
    }
    public List<User> convert(List<UserEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }
}
