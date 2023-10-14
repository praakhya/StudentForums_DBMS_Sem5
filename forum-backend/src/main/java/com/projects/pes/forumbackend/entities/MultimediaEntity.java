package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "multimedias")
public class MultimediaEntity extends ResourceEntity {
    private Date dateOfCreation;
    private String caption;
    @Lob
    private Blob file;
}
