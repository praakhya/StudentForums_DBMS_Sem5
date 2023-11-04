package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.ResourceEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.Post;
import com.projects.pes.forumbackend.pojo.Resource;
import com.projects.pes.forumbackend.repositories.ResourceRepository;
import com.projects.pes.forumbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostMapper {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceRepository resourceRepository;
    public PostEntity convert(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.id());
        postEntity.setType(post.type());
        postEntity.setTitle(post.title());
        postEntity.setContent(post.content());
        postEntity.setPosterId(post.posterId());
        postEntity.setParentId(post.parentId());
        postEntity.setResources(post.resourceIds().stream().map(r -> resourceRepository.findById(r).orElseThrow(()->new EntityDoesntExist(r.toString(), "resource"))).collect(Collectors.toList()));
        return postEntity;
    }

    public Post convert(PostEntity postEntity,
                        UserRepository userRepository) {
        UserEntity userEntity = userRepository.findById(postEntity.getPosterId()).orElseThrow(() -> new UsernameNotFoundException(postEntity.getPosterId().toString()));
        return new Post(postEntity.getId(),
                postEntity.getType(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getPosterId(),
                userEntity.getUsername(),
                userEntity.getPicture().getUrl(),
                postEntity.getParentId(),
                postEntity.getPosts() == null ? new ArrayList<>() : postEntity.getPosts().stream().map(p -> convert(p, userRepository)).collect(Collectors.toList()),
                postEntity.getResources() == null ? new ArrayList<>() : postEntity.getResources().stream().map(ResourceEntity::getId).collect(Collectors.toList()));
    }
}
