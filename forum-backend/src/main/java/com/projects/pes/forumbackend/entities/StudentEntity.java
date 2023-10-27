package com.projects.pes.forumbackend.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
public class StudentEntity extends UserEntity {
    private String rollNo;
    private String department;
    private UUID classId;
    private UUID forumId;
    private List<String> memberships;
    private List<String> publications;
    private List<String> skills;
}
