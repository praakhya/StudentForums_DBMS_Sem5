package com.projects.pes.forumbackend.entities;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class FacultyPublicationEntity {
    private UUID userId;
    private String publications;
}
