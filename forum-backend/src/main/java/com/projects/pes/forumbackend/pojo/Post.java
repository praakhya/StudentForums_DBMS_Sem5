package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ResourceEntity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;
import java.util.UUID;

public record Post(
         UUID id,
         String type,
         String title,
         String Content,
         UUID posterId,
         Set<ResourceEntity>resources
) {
}
