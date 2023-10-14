package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

import java.sql.Blob;
import java.util.Set;
import java.util.UUID;

public record User(
         UUID id,
         String username,
         String email,
         String name,
         String password,
         Blob picture,
         String contact,
         Set<ForumEntity>forums
) {
}
