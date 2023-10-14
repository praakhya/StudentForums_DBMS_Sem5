package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.ResourceEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

public record Forum(
         UUID id,
         FacultyEntity admin,
         String name,
         Set<PostEntity> posts,
         Set<ResourceEntity> resources,
         Set<UserEntity> users
) {
}
