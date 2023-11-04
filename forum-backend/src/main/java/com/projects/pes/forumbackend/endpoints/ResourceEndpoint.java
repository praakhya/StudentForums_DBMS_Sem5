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
    public Optional<Resource> uploadFile(@RequestParam("uploadedFile") MultipartFile file) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String filename = file.getOriginalFilename() != null
                ? file.getOriginalFilename()
                : (file.getName() != null ? file.getName() : "unknownFile");
        try {
            System.out.println("Uploading file " + username + " name = " + file.getName() + " original name = " + file.getOriginalFilename() + " mimetype = " + file.getContentType());
            return Optional.of(resourceService.updateFile(username, filename, file.getBytes(), file.getContentType()));
        }
        catch (IOException ex) {
            throw new FileParsingFailed(file.getName(), ex);
        }
    }
    @RequestMapping(value = Constants.Paths.RESOURCE_GET_PATH, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFile(@PathVariable("id") UUID id, @PathVariable("filename") String filename) {
        Resource resource = resourceService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(resource.contentType()))
                .header("Content-disposition", String.format("attachment; filename=%s", resource.name()))
                .body(resource.content());

    }
    @RequestMapping(value = Constants.Paths.RESOURCE_FOR_ID_PATH, method =  RequestMethod.DELETE)
    public Optional<Resource> deleteResource(@PathVariable("id") UUID id) {
        return Optional.of(resourceService.deleteFile(id));
    }
    @RequestMapping(value = Constants.Paths.RESOURCE_FOR_ID_PATH, method =  RequestMethod.GET)
    public Optional<Resource> getResourceById(@PathVariable("id") UUID id) {
        return Optional.of(resourceService.getResourceById(id));
    }
}
