package com.projects.pes.forumbackend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Blob;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"})
})
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String password;
    @Embedded
    private PictureEntity picture;
    private String contact;
    @ManyToMany
    private Set<ForumEntity> forums;

}
