package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.ForumMapper;
import com.projects.pes.forumbackend.mappers.PostMapper;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.Post;
import com.projects.pes.forumbackend.repositories.ForumRepository;
import com.projects.pes.forumbackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ForumMapper forumMapper;

    @Autowired
    private PostMapper postMapper;

    public Iterable<Forum> getForums() {
        List<Forum> forumList = new ArrayList<>();
        forumRepository.findAll().iterator().forEachRemaining(e -> forumList.add(forumMapper.convert(e)));
        return forumList;
    }
    public Forum getForum(String name) {
        return forumMapper.convert(forumRepository
                .findByName(name).orElseThrow(()->{throw new EntityDoesntExist(name,"Forum");}));
    }
    public Forum save(Forum forum) {
        return forumMapper.convert(forumRepository.save(forumMapper.convert(forum)));
    }
    public Post createPostInForum(UUID forumId, Post post) {
        Optional<ForumEntity>optionalForumEntity = forumRepository.findById(forumId);
        if (optionalForumEntity.isPresent()) {
            PostEntity postEntity = postMapper.convert(post);
            postEntity = postRepository.save(postEntity);
            ForumEntity forumEntity = optionalForumEntity.get();
            if (post.parentId()==null) {
                if (forumEntity.getPosts()==null) {
                    forumEntity.setPosts(new HashSet<>());
                }
                forumEntity.getPosts().add(postEntity);
            }
            else {
                Optional<PostEntity> optionalPostEntity = postRepository.findById(post.parentId());
                if (optionalPostEntity.isPresent()) {
                    PostEntity parentPostEntity = optionalPostEntity.get();
                    if (parentPostEntity.getPosts()==null) {
                        parentPostEntity.setPosts(new HashSet<>());
                    }
                    parentPostEntity.getPosts().add(postEntity);
                    postRepository.save(parentPostEntity);
                }
            }

            forumRepository.save(forumEntity);
            return postMapper.convert(postEntity);
        }
        throw new EntityDoesntExist(forumId.toString(), "Forum");
    }
    public Forum delete(String name) {
        Optional<ForumEntity> optionalForumEntity = forumRepository.findByName(name);
        if (optionalForumEntity.isPresent()) {
            ForumEntity entity = optionalForumEntity.get();
            forumRepository.delete(entity);
            return forumMapper.convert(entity);
        }
        throw new EntityDoesntExist(name,"Forum");
    }
}
