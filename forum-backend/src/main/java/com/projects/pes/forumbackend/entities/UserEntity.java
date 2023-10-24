package com.projects.pes.forumbackend.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Blob;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Nonnull
    private String username;
    private String email;
    @Nonnull
    private String name;
    @Nonnull
    private String password;
    @Embedded
    private PictureEntity picture;
    private String contact;
    @ManyToMany
    private Set<ForumEntity> forums;

}
