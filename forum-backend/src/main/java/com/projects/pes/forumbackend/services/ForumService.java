package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.exceptions.SubscriptionMappingDoesNotExist;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.ForumMapper;
import com.projects.pes.forumbackend.mappers.PostMapper;
import com.projects.pes.forumbackend.mappers.UserMapper;
import com.projects.pes.forumbackend.pojo.*;
import com.projects.pes.forumbackend.repositories.FacultyRepository;
import com.projects.pes.forumbackend.repositories.ForumRepository;
import com.projects.pes.forumbackend.repositories.PostRepository;
import com.projects.pes.forumbackend.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForumMapper forumMapper;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EntityManager entityManager;

    public Iterable<Forum> getForums() {
        List<Forum> forumList = new ArrayList<>();
        forumRepository.findAll().iterator().forEachRemaining(e -> forumList.add(forumMapper.convert(e)));
        return forumList;
    }
    public List getAllForums() {
        return (List)entityManager.createNativeQuery("SELECT BIN_TO_UUID(f.id) as id, BIN_TO_UUID(f.admin_id) as adminId, f.name as name FROM forums f").getResultStream().collect(Collectors.toList());
    }
    public Forum getForum(String name) {
        return forumMapper.convert(forumRepository
                .findByName(name).orElseThrow(()->{throw new EntityDoesntExist(name,"Forum");}));
    }
    public Forum getForum(UUID id) {
        return forumMapper.convert(forumRepository
                .findById(id).orElseThrow(()->{throw new EntityDoesntExist(id.toString(),"Forum");}));
    }
    @Transactional
    public Forum save(CreateForum forum) {
        FacultyEntity facultyEntity
                = facultyRepository.findById(forum.adminId()).orElseThrow(()->new EntityDoesntExist(forum.adminId().toString(), "faculty admin"));
        ForumEntity forumEntity = forumMapper.convert(forum, facultyEntity);
        forumEntity.setUsers(new ArrayList<>());
        UserEntity facultyUserEntity = userRepository.findById(facultyEntity.getId()).get();
        if (facultyUserEntity.getForums() == null) {
            facultyUserEntity.setForums(new ArrayList<>());
        }
        facultyUserEntity.getForums().add(forumEntity);
        forumEntity.setUsers(new ArrayList<>());
        forumEntity.getUsers().add(facultyUserEntity);
        forumEntity = forumRepository.save(forumEntity);
        return forumMapper.convert(forumEntity);
    }
    @Transactional
    public Post createPostInForum(UUID forumId, Post jsonPost) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Post post = new Post(null, jsonPost.type(), jsonPost.title(), jsonPost.content(), userEntity.getId(), userEntity.getUsername(), userEntity.getPicture().getUrl(), jsonPost.parentId(), jsonPost.posts(), jsonPost.resources());
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
                        parentPostEntity.setPosts(new ArrayList<>());
                    }
                    parentPostEntity.getPosts().add(postEntity);
                    postRepository.save(parentPostEntity);
                }
            }

            forumRepository.save(forumEntity);
            return postMapper.convert(postEntity, userRepository);
        }
        throw new EntityDoesntExist(forumId.toString(), "Forum");
    }
    @Transactional
    public Iterable<Post> getPosts(UUID forumId) {
        ForumEntity forumEntity = forumRepository.findById(forumId).orElseThrow(() -> new EntityDoesntExist(forumId.toString(), "forum"));
        return forumEntity.getPosts() == null ? new ArrayList<>() : forumEntity.getPosts().stream().map(p -> postMapper.convert(p, userRepository)).collect(Collectors.toList());
    }
    @Transactional
    public Forum delete(String name) {
        Optional<ForumEntity> optionalForumEntity = forumRepository.findByName(name);
        if (optionalForumEntity.isPresent()) {
            ForumEntity entity = optionalForumEntity.get();
            forumRepository.delete(entity);
            return forumMapper.convert(entity);
        }
        throw new EntityDoesntExist(name,"Forum");
    }

    @Transactional
    public Forum subscribeUserToForum(UUID forumId, UUID userId) {
        Optional<ForumEntity> optionalForumEntity = forumRepository.findById(forumId);
        ForumEntity forumEntity = optionalForumEntity.orElseThrow(()->new EntityDoesntExist(forumId.toString(), "forum"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()->new UserDoesntExist(userId.toString()));
        if (forumEntity.getUsers()==null) {
            forumEntity.setUsers(new ArrayList<>());
        }
        forumEntity.getUsers().add(userEntity);
        if (userEntity.getForums()==null) {
            userEntity.setForums(new ArrayList<>());
        }
        userEntity.getForums().add(forumEntity);
        forumEntity = forumRepository.save(forumEntity);
        return forumMapper.convert(forumEntity);
    }
    public Forum subscribeUserToForum(UUID forumId, String username) {
        Optional<ForumEntity> optionalForumEntity = forumRepository.findById(forumId);
        ForumEntity forumEntity = optionalForumEntity.orElseThrow(()->new EntityDoesntExist(forumId.toString(), "forum"));
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->new UserDoesntExist(username));
        if (forumEntity.getUsers()==null) {
            forumEntity.setUsers(new ArrayList<>());
        }
        forumEntity.getUsers().add(userEntity);
        if (userEntity.getForums()==null) {
            userEntity.setForums(new ArrayList<>());
        }
        userEntity.getForums().add(forumEntity);
        userRepository.save(userEntity);
        forumEntity = forumRepository.save(forumEntity);
        return forumMapper.convert(forumEntity);
    }
    @Transactional
    public Forum unsubscribeUserToForum(UUID forumId, UUID userId) {
        Optional<ForumEntity> optionalForumEntity = forumRepository.findById(forumId);
        ForumEntity forumEntity = optionalForumEntity.orElseThrow(()->new EntityDoesntExist(forumId.toString(), "forum"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()->new UserDoesntExist(userId.toString()));
        if (forumEntity.getUsers() != null &&
                userEntity.getForums() != null &&
                forumEntity.getUsers().contains(userEntity) &&
                userEntity.getForums().contains(forumEntity)) {
            userEntity.getForums().remove(forumEntity);
            forumEntity.getUsers().remove(userEntity);
            userRepository.save(userEntity);
            return forumMapper.convert(forumEntity);
        }
        throw new SubscriptionMappingDoesNotExist(userEntity.getUsername(), forumEntity.getName());

    }

}
