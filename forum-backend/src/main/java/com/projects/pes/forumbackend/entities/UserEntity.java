package com.projects.pes.forumbackend.entities;

import com.projects.pes.forumbackend.pojo.UserRole;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Nonnull
    private String email;
    @Nonnull
    private String name;
    @Nonnull
    private String password;
    @Embedded
    private PictureEntity picture;
    private String contact;
    private UserRole role;
    @ManyToMany
    private Set<ForumEntity> forums;

}
