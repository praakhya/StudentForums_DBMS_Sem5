package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.exceptions.FileParsingFailed;
import com.projects.pes.forumbackend.mappers.SectionMapper;
import com.projects.pes.forumbackend.pojo.*;
import com.projects.pes.forumbackend.repositories.SectionRepository;
import com.projects.pes.forumbackend.repositories.UserRepository;
import com.projects.pes.forumbackend.services.StudentService;
import com.projects.pes.forumbackend.services.UserService;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@RestController
@RequestMapping(Constants.Paths.USER_PATH)
public class UserEndpoint {
    @Autowired
    private UserService userService;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private UserRepository userRepository;
        @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.of(userService.getUser(username));
    }
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getUser(@PathVariable("username") String username) {
        return Optional.of(userService.getUser(username));
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> save(@RequestBody User user) {
        return Optional.of(userService.save(user));
    }
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> deleteUser(@PathVariable("username") String username) {
        return Optional.of(userService.delete(username));
    }
    @RequestMapping(value = Constants.Paths.IMAGE_UPLOAD_PATH, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProfileImage uploadImage(@PathVariable("username") String username, @RequestParam("image") MultipartFile file) {
        try {
            return userService.updateProfileImage(username, file.getBytes(), file.getContentType());
        }
        catch (IOException ex) {
            throw new FileParsingFailed(file.getName(), ex);
        }
    }
    @RequestMapping(value = Constants.Paths.IMAGE_UPLOAD_PATH, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("username") String username) {
        Optional<ProfileImage> optionalProfileImage = userService.getImage(username);
        if (optionalProfileImage.isPresent()) {

            ProfileImage profileImage = optionalProfileImage.get();
            return ResponseEntity.ok().contentType(MediaType.valueOf(profileImage.mimeType())).body(profileImage.image());
        }
        else {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header("Location", Constants.Paths.DUMMY_PROFILE_PICTURE).build();
        }
    }
    @RequestMapping(value = "/forums", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Forum> getUserForums() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserForums(authentication.getName());
    }
}
