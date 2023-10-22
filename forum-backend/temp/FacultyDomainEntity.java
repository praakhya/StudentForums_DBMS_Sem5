package com.projects.pes.forumbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

import java.sql.Blob;
import java.util.UUID;

@Entity
public class FacultyDomainEntity {
    private UUID userId;
    private String domain;
}
