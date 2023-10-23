package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
public class StudentEntity extends UserEntity {
    private String rollNo;
    private String department;
    private UUID classID;
    private UUID forumID;
    private List<String> memberships;
    private List<String> publications;
    private List<String> skills;
}
