package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "forums")
@Data
public class ForumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
