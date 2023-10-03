package com.projects.pes.forumbackend.controller;

import com.projects.pes.forumbackend.pojo.Hello;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional; //Optional type lets your return Null without exception

@RestController // indicating this is a controller
@RequestMapping(Constants.Paths.HELLO_PATH) // path of endpoint
public class HelloWorldEndpoint {
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //if the media type is json get will be done by this method. additionally first argument can be path
    public Optional<Hello> getHello() {
        Hello h = new Hello("Hello", "World");
        return Optional.of(h); // allows to pass null
    }
}
