package com.projects.pes.forumbackend.pojo;

import jakarta.persistence.Id;

import java.util.UUID;

public record Section(
        @Id
         UUID id,
         UUID classRepID,
         UUID classTeacherID,
         String name
) {
}
