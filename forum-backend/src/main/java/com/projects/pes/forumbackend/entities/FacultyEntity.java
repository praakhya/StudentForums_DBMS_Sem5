package com.projects.pes.forumbackend.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "faculty")
public class FacultyEntity extends UserEntity {

    private String jobTitle;
    private String department;
    private List<String> domains;
    private List<String> publications;
}
