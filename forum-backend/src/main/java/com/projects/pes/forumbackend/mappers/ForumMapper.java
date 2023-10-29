package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.pojo.CreateForum;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForumMapper {
    public ForumEntity convert(Forum forum) {
        ForumEntity forumEntity = new ForumEntity();
        forumEntity.setId(forum.id());
        forumEntity.setName(forum.name());
        return forumEntity;
    }
    public ForumEntity convert(CreateForum forum, FacultyEntity facultyEntity) {
        ForumEntity forumEntity = new ForumEntity();
        forumEntity.setName(forum.name());
        forumEntity.setAdmin(facultyEntity);
        return forumEntity;
    }

    public Forum convert(ForumEntity forumEntity) {
        FacultyEntity facultyEntity = forumEntity.getAdmin();
        return new Forum(forumEntity.getId(),
                new Faculty(facultyEntity.getId(), facultyEntity.getUsername(),facultyEntity.getEmail(), facultyEntity.getName(),null, null, null, null, null, null, null, null, null),
                forumEntity.getName(),
                null,
                null,
                forumEntity.getUsers().stream().map(UserEntity::getId).collect(Collectors.toSet()));
    }
    public List<Forum> convert(List<ForumEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }


}
