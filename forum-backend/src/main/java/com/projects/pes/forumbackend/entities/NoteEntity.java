package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "notes")
public class NoteEntity extends ResourceEntity{
    private UUID authorId;
    private String content;
    private String title;
}
