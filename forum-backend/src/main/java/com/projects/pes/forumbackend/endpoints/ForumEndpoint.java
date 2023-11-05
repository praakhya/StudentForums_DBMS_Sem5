package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.pojo.*;
import com.projects.pes.forumbackend.repositories.ForumRepository;
import com.projects.pes.forumbackend.services.ForumService;
import com.projects.pes.forumbackend.utils.Constants;
import jakarta.persistence.EntityManager;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.*;


@RestController
@RequestMapping(Constants.Paths.FORUM_PATH)
public class ForumEndpoint {

    @Autowired
    private ForumService forumService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ForumRepository forumRepository;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Forum> getForums() {
        return forumService.getForums();
    }
    @RequestMapping(value = Constants.Paths.FORUM_GET_ONE_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> getForum(@PathVariable("id") UUID id) {
        return Optional.of(forumService.getForum(id));
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> save(@RequestBody CreateForum forum) {
        return Optional.of(forumService.save(forum));
    }

    @RequestMapping(value = Constants.Paths.FORUM_GET_ONE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> deleteForum(@PathVariable("name") String name) {
        return Optional.of(forumService.delete(name));
    }

    @RequestMapping(value=Constants.Paths.POST_IN_A_FORUM_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Post> save(@PathVariable("forumId") UUID forumId, @RequestBody Post post) {
        return Optional.of(forumService.createPostInForum(forumId,post));
    }
    @RequestMapping(value=Constants.Paths.DELETE_POST_FROM_A_FORUM_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Post> delete(@PathVariable("forumId") UUID forumId, @PathVariable("postId") UUID postId) {
        return Optional.of(forumService.deletePostFromAForum(forumId,postId));
    }
    @RequestMapping(value=Constants.Paths.POST_IN_A_FORUM_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Iterable<Post>> getPosts(@PathVariable("forumId") UUID forumId) {
        return Optional.of(forumService.getPosts(forumId));
    }
/*    @RequestMapping(value=Constants.Paths.SUBSCRIBE_TO_A_FORUM_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> subscribe(@PathVariable("forumId") UUID forumId, @PathVariable("userId") UUID userId) {
        return Optional.of(forumService.subscribeUserToForum(forumId,userId));
    }*/
    @RequestMapping(value=Constants.Paths.SUBSCRIBE_TO_A_FORUM_WITH_USERNAME_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> subscribe(@PathVariable("forumId") UUID forumId, @PathVariable("username") String username) {

        //String query = String.format("call notifyUserAddToForum(\"%s\",\"%s\");",username, forumId.toString());
        //System.out.println("Query:"+query);
        forumRepository.addSubscribeNotification(username, forumId.toString());
        //entityManager.createNativeQuery(query).;//executeUpdate();getMaxResults();//getResultList();
        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("notification", Long.parseLong(results.get(0).toString()));
        //System.out.println("-------------RETURNED-------------"); // method 1
        //System.out.println(results); // method 1
        return Optional.of(forumService.subscribeUserToForum(forumId,username));
    }
    @RequestMapping(value=Constants.Paths.SUBSCRIBE_TO_A_FORUM_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> unsubscribe(@PathVariable("forumId") UUID forumId, @PathVariable("userId") UUID userId) {
        forumRepository.addUnsubscribeNotification(userId.toString(), forumId.toString());
        return Optional.of(forumService.unsubscribeUserToForum(forumId,userId));
    }
    @RequestMapping(value=Constants.Paths.GET_ALL_BRIEF_FORUMS_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Forum> getAllForums() {
        return forumService.getAllForums();
    };
    /*@RequestMapping(value=Constants.Paths.GET_ALL_FORUM_USERS_PATH, method=RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserInAForum> getUsers(@PathVariable("forumId") UUID forumId) {
        return
    }*/

}
