package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.ResourceEntity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Post(
         UUID id,
         String type,
         String title,
         String content,
         UUID posterId,
         String posterName,
         String posterImgUrl,
         UUID parentId,
         List<Post> posts,
         List<Resource> resources
) {
}
