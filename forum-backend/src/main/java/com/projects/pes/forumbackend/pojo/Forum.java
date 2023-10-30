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
         Faculty admin,
         String name,
         Set<Post> posts,
         Set<Resource> resources,
         Set<UserInAForum> users
) {
}
