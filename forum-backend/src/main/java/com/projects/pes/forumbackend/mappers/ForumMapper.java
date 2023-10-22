package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.ForumEntity;
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
        forumEntity.setAdmin(forum.admin());
        forumEntity.setName(forum.name());
        forumEntity.setPosts(forum.posts());
        forumEntity.setResources(forum.resources());
        forumEntity.setUsers(forum.users());
        return forumEntity;
    }

    public Forum convert(ForumEntity forumEntity) {
        return new Forum(forumEntity.getId(),
                forumEntity.getAdmin(),
                forumEntity.getName(),
                forumEntity.getPosts(),
                forumEntity.getResources(),
                forumEntity.getUsers());
    }
    public List<Forum> convert(List<ForumEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }


}
