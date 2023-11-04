package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.*;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.ResourceMapper;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.pojo.Resource;
import com.projects.pes.forumbackend.pojo.UserRole;
import com.projects.pes.forumbackend.repositories.ResourceRepository;
import com.projects.pes.forumbackend.repositories.UserRepository;
import com.projects.pes.forumbackend.utils.Constants;
import jakarta.persistence.ManyToMany;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    UserRepository userRepository;
    public Resource getFile(UUID resourceId) {
        return resourceMapper.convert(resourceRepository.findById(resourceId).orElseThrow(()->new EntityDoesntExist(resourceId.toString(), "resource")), true);
    }
    @Transactional
    public Resource updateFile(String username,
                               String filename,
                               byte[] bytes,
                               String mimeType) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserDoesntExist(username));
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setValidatorId(userEntity.getRole().equals(UserRole.FACULTY) ? userEntity.getId() : null);
        resourceEntity.setValidated(userEntity.getRole().equals(UserRole.FACULTY));
        resourceEntity.setDateOfPublish(new Date());
        resourceEntity.setContentType(mimeType);
        resourceEntity.setContentData(bytes);
        resourceEntity.setOwnerName(username);
        resourceEntity.setName(filename);
        return resourceMapper.convert(resourceRepository.save(resourceEntity), false);
    }

    public Iterable<Resource> getResourcesOfOwner(String username) {
        return  resourceRepository.findByUsername(username).stream().map(r -> resourceMapper.convert(r, false)).collect(Collectors.toList());
    }
    public Resource deleteFile(UUID resourceId) {
        ResourceEntity resourceEntity = resourceRepository.findById(resourceId).orElseThrow(() -> new EntityDoesntExist(resourceId.toString(), "resource"));
        Resource resource = resourceMapper.convert(resourceEntity,false);
        resourceRepository.delete(resourceEntity);
        return resource;
    }
    public Resource getResourceById(UUID resourceId) {
        return  resourceMapper.convert(resourceRepository.findById(resourceId).isPresent() ? resourceRepository.findById(resourceId).get():null,true);
    }

}
