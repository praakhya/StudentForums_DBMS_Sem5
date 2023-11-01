package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record Resource(
         UUID id,
         String name,
         String ownerName,
         UUID validatorId,
         Boolean validated,
         Date dateOfPublish,
         String contentType,
         byte[] content,
         String url
) {
}
