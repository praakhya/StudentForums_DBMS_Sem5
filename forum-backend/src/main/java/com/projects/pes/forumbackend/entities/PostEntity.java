package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String type;
    private String title;
    private String content;
    private UUID posterId;
    @ManyToMany
    private Set<ResourceEntity> resources;
}
