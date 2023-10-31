package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.ResourceEntity;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.Post;
import com.projects.pes.forumbackend.pojo.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostMapper {
    public PostEntity convert(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.id());
        postEntity.setType(post.type());
        postEntity.setTitle(post.title());
        postEntity.setContent(post.content());
        postEntity.setPosterName(post.posterName());
        postEntity.setParentId(post.parentId());
        return postEntity;
    }

    public Post convert(PostEntity postEntity) {
        return new Post(postEntity.getId(),
                postEntity.getType(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getPosterName(),
                postEntity.getParentId(),
                postEntity.getPosts().stream().map(p -> new Post(p.getId(),p.getType(),p.getTitle(),p.getContent(),p.getPosterName(),p.getParentId(),null,null)).collect(Collectors.toSet()),
                postEntity.getResources().stream().map(r -> new Resource(r.getId(),null, r.getValidated(), r.getDateOfPublish(), r.getContentType())).collect(Collectors.toSet()));
    }

    public List<Post> convert(List<PostEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }
}
