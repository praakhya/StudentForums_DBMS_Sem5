package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "resources")
public class ResourceEntity {
    @Id
    private UUID id;
    private UUID validatorId;
    private Boolean validated;
    private Date dateOfPublish;
    private String contentType;
    @ManyToMany
    private Set<PostEntity> posts;
    @ManyToMany
    private Set<ForumEntity> forums;
}
