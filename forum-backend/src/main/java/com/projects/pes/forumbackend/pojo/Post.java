package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.ResourceEntity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;
import java.util.UUID;

public record Post(
         UUID id,
         String type,
         String title,
         String content,
         UUID posterId,
         UUID parentId,
         Set<PostEntity> posts,
         Set<ResourceEntity>resources
) {
}
