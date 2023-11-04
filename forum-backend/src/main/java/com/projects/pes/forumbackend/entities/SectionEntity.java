package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "sections")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private StudentEntity classRep;
    @OneToOne
    private FacultyEntity classTeacher;
    private String name;
}

