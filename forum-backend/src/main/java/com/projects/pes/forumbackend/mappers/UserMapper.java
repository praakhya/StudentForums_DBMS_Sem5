package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.*;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.pojo.*;
import com.projects.pes.forumbackend.repositories.SectionRepository;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    @Autowired
    private SectionRepository sectionRepository;
    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setPicture(new PictureEntity(null, null, Constants.Paths.DUMMY_PROFILE_PICTURE));
        userEntity.setContact(user.getContact());
        userEntity.setRole(user.getRole());
        return userEntity;
    }
    public  User convert(UserEntity userEntity, SectionMapper sectionMapper) {
        PictureEntity pictureEntity = userEntity.getPicture();
        byte[] imageData = null;
        String mimeType = null;
        if (pictureEntity != null) {
            imageData = pictureEntity.getImageData();
            mimeType = pictureEntity.getMimeType();
        }
        SectionEntity sectionEntity = sectionRepository.findByUsername(userEntity.getUsername()).orElseThrow(() -> new EntityDoesntExist(userEntity.getUsername(), "section"));
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getPicture().getUrl() + "?" + System.currentTimeMillis(),
                sectionMapper.convert(sectionEntity),
                userEntity.getContact(),
                userEntity.getForums() == null ? new HashSet<>() : userEntity.getForums().stream().map(f->new Forum(f.getId(), null, f.getName(), null, null, null, f.getSection() == null ? null : f.getSection().getId())).collect(Collectors.toSet()),
                userEntity.getRole()
        );
    }
    public UserEntity convert(UserInAForum user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.id());
        userEntity.setUsername(user.username());
        return userEntity;
    }
}
