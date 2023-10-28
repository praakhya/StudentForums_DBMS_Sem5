package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.exceptions.FileParsingFailed;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.services.FacultyService;
import com.projects.pes.forumbackend.services.UserService;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Constants.Paths.FACULTY_PATH)
public class FacultyEndpoint {
    @Autowired //automatically finds beans and injects it.
    private FacultyService facultyService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Faculty> getFaculty() {

        return facultyService.getFaculties();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Faculty> getFaculty(@PathVariable("username") String username) {
        return Optional.of(facultyService.getFaculty(username));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Faculty> save(@RequestBody Faculty faculty) {
        return Optional.of(facultyService.save(faculty));
    }
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Faculty> deleteFaculty(@PathVariable("username") String username) {
        return Optional.of(facultyService.delete(username));
    }
    @RequestMapping(value = Constants.Paths.IMAGE_UPLOAD_PATH, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProfileImage  uploadImage(@PathVariable("username") String username, @RequestParam("image") MultipartFile file) {
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
