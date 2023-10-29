package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "resources")
@Data
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID validatorId;
    private Boolean validated;
    private Date dateOfPublish;
    private String contentType;
    private byte[] contentData;
    @ManyToMany
    private Set<PostEntity> posts;
    @ManyToMany
    private Set<ForumEntity> forums;
}
