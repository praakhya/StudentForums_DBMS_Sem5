package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.mappers.PostMapper;
import com.projects.pes.forumbackend.pojo.Post;
import com.projects.pes.forumbackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    public Iterable<Post> getPosts() {
        List<Post> postList = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(e -> postList.add(postMapper.convert(e)));
        return postList;
    }
    public  Post getPost(String title) {
        return postMapper.convert(postRepository
                .findByTitle(title).orElseThrow(() -> {throw new EntityDoesntExist(title,"Post");}));
    }
    public Post save(Post post) {
        return postMapper.convert(postRepository.save(postMapper.convert(post)));
    }

}
