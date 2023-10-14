package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "forums")
public class ForumEntity {
    @Id
    private UUID id;
    @ManyToOne
    private FacultyEntity admin;
    private String name;
    @ManyToMany
    @JoinColumn(name="id", referencedColumnName="id", foreignKey=@ForeignKey(name = "Fk_forums_posts"))
    private Set<PostEntity> posts;
    @ManyToMany
    private Set<ResourceEntity> resources;
    @ManyToMany
    private Set<UserEntity> users;
}
