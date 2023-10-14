package com.projects.pes.forumbackend.pojo;

import jakarta.persistence.Lob;

import java.sql.Blob;
import java.util.Date;

public record Multimedia(
         Date dateOfCreation,
         String caption,
         Blob file
) {
}
