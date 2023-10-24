package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.PostEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends CrudRepository<PostEntity, UUID> {

    @Query("select p from PostEntity p where p.title = :title")
    Optional<PostEntity> findByTitle(String title);
}
