package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.pojo.Token;
import com.projects.pes.forumbackend.pojo.User;
import com.projects.pes.forumbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    public Token authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        UserEntity userEntity = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserDoesntExist(user.getUsername()));
        String jwtToken = jwtService.generateToken(userEntity);
        return new Token(jwtToken);


    }

}
