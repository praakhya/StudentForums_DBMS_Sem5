package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.pojo.Token;
import com.projects.pes.forumbackend.pojo.User;
import com.projects.pes.forumbackend.services.AuthenticationService;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Paths.AUTH_PATH)
public class AuthenticationEndpoint {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<Token> authenticate(
            @RequestBody User user
            ) {
        return ResponseEntity.ok(authenticationService.authenticate(user));

    }
}
