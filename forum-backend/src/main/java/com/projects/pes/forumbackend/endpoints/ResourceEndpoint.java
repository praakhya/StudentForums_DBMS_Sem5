package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.entities.ResourceEntity;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.exceptions.FileParsingFailed;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.pojo.Resource;
import com.projects.pes.forumbackend.services.ResourceService;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Constants.Paths.RESOURCE_PATH)
public class ResourceEndpoint {
    @Autowired
    ResourceService resourceService;
    @RequestMapping(value = Constants.Paths.RESOURCE_OF_USER_GET_PATH, method = RequestMethod.GET)
    public Iterable<Resource> getResources(@PathVariable("username") String username) {
        return resourceService.getResourcesOfOwner(username);
    }
    @RequestMapping(value = Constants.Paths.RESOURCE_UPLOAD_PATH,
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Optional<Resource> uploadImage(@RequestParam("uploadedFile") MultipartFile file) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            return Optional.of(resourceService.updateFile(username, file.getBytes(), file.getContentType()));
        }
        catch (IOException ex) {
            throw new FileParsingFailed(file.getName(), ex);
        }
    }
    @RequestMapping(value = Constants.Paths.RESOURCE_GET_PATH, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") UUID id) {
        Resource resource = resourceService.getFile(id);
        return ResponseEntity.ok().contentType(MediaType.valueOf(resource.contentType())).body(resource.content());
    }
}
