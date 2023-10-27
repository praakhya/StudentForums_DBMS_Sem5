package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String password;
    private byte[] imageData;
    private String mimeType;
    private String contact;
    private Set<ForumEntity>forums;
    private UserRole role;
}
