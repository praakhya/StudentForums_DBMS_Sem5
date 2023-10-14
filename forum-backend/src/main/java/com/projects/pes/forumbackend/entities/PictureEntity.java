package com.projects.pes.forumbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Data;

@Embeddable
@Data
public class PictureEntity {
    public PictureEntity() {
    }

    public PictureEntity(byte[] imageData, String mimeType) {
        this.imageData = imageData;
        this.mimeType = mimeType;
    }

    @Lob
    @Column(name = "picture", columnDefinition="BLOB")
    private byte[] imageData;
    private String mimeType;
}
