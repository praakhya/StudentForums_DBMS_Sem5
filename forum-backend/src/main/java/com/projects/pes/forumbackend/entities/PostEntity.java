package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    private UUID id;
    private String type;
    private String title;
    private String Content;
    private UUID posterId;
    @ManyToMany
    private Set<ResourceEntity> resources;
}
