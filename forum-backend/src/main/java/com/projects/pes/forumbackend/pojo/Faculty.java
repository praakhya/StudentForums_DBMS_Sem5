package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

import java.sql.Blob;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Faculty(UUID id,
                      String name,
                      String username,
                      String password,
                      String contact,
                      String jobTitle,
                      String department,
                      List<String>domains,
                      List<String> publications) {
}
