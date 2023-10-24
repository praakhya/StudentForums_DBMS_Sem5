package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.Post;
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
        postEntity.setPosterId(post.posterId());
        postEntity.setParentId(post.parentId());
        postEntity.setPosts(post.posts());
        postEntity.setResources(post.resources());
        return postEntity;
    }

    public Post convert(PostEntity postEntity) {
        return new Post(postEntity.getId(),
                postEntity.getType(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getPosterId(),
                postEntity.getParentId(),
                postEntity.getPosts(),
                postEntity.getResources());
    }

    public List<Post> convert(List<PostEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }
}
