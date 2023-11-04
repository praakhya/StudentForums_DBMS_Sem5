package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.StudentEntity;
import jakarta.persistence.OneToOne;

import java.util.UUID;

public record Section(
        UUID id,
        String classRepUsername,
        String classTeacherUsername,
        String name) {
}
