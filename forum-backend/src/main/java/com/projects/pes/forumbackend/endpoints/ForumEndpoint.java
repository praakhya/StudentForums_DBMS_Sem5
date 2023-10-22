package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.Post;
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
    public Optional<Forum> getForum(@PathVariable("name") String name) {
        return Optional.of(forumService.getForum(name));
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Forum> save(@RequestBody Forum forum) {
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

}
