package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.exceptions.FileParsingFailed;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.services.StudentService;
import com.projects.pes.forumbackend.services.UserService;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(Constants.Paths.STUDENT_PATH)
public class StudentEndpoint {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Student> getStudent() {
        return studentService.getStudents();
    }
    @RequestMapping(method = RequestMethod.PATCH, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Optional<Student> updateStudent(@RequestBody Student student) {
        return Optional.of(studentService.updateStudent(student));

    }
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Student> getStudent(@PathVariable("username") String username) {
        return Optional.of(studentService.getStudent(username));
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Student> save(@RequestBody Student student) {
        return Optional.of(studentService.save(student));
    }
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Student> deleteStudent(@PathVariable("username") String username) {
        return Optional.of(studentService.delete(username));
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
}
