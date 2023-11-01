package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.ResourceEntity;
import com.projects.pes.forumbackend.pojo.Resource;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class ResourceMapper {
    public Resource convert(ResourceEntity resourceEntity, boolean addContent) {
        return new Resource(
                resourceEntity.getId(),
                resourceEntity.getName(),
                resourceEntity.getOwnerName(),
                resourceEntity.getValidatorId(),
                resourceEntity.getValidated(),
                resourceEntity.getDateOfPublish(),
                resourceEntity.getContentType(),
                addContent ? resourceEntity.getContentData() : null,
                Constants.BASE_PATH+Constants.Paths.RESOURCE_GET_PATH.replace("{id}",resourceEntity.getId().toString())
        );
    }

}
