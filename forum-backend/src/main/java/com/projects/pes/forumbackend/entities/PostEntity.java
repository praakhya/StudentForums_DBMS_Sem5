package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
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
    @Column(name = "content", columnDefinition="TEXT")
    private String content;
    private UUID posterId;
    private UUID parentId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PostEntity> posts;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "posts_resources",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private List<ResourceEntity> resources;
}
