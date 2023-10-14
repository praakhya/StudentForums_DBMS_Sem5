package com.projects.pes.forumbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "sections")
public class SectionEntity {
    @Id
    private UUID id;
    private UUID classRepID;
    private UUID classTeacherID;
    private String name;
}

