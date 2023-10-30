package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.pojo.*;
import com.projects.pes.forumbackend.services.ForumService;
import com.projects.pes.forumbackend.utils.Constants;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(Constants.Paths.FORUM_PATH)
public class ForumEndpoint {

    @Autowired
    private ForumService forumService;

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
/*    @RequestMapping(value=Constants.Paths.SUBSCRIBE_TO_A_FORUM_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> subscribe(@PathVariable("forumId") UUID forumId, @PathVariable("userId") UUID userId) {
        return Optional.of(forumService.subscribeUserToForum(forumId,userId));
    }*/
    @RequestMapping(value=Constants.Paths.SUBSCRIBE_TO_A_FORUM_WITH_USERNAME_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> subscribe(@PathVariable("forumId") UUID forumId, @PathVariable("username") String username) {
        return Optional.of(forumService.subscribeUserToForum(forumId,username));
    }
    @RequestMapping(value=Constants.Paths.SUBSCRIBE_TO_A_FORUM_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> unsubscribe(@PathVariable("forumId") UUID forumId, @PathVariable("userId") UUID userId) {
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
