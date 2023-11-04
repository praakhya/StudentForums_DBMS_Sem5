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
    private String name;
    private UUID validatorId;
    private Boolean validated;
    private Date dateOfPublish;
    private String contentType;
    @Column(name = "contentData", columnDefinition="LONGBLOB")
    private byte[] contentData;
    private String ownerName;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "resources")
    private Set<PostEntity> posts;
    @ManyToMany
    private Set<ForumEntity> forums;
}
