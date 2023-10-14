package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface FacultyRepository extends CrudRepository<FacultyEntity, UUID> {
    @Query("select f from FacultyEntity f, UserEntity ue where ue.id = f.id and ue.username = :username")
    Optional<FacultyEntity> findByUsername(String username);
    @Query("SELECT f FROM FacultyEntity f WHERE f.email = :email")
    Optional<FacultyEntity> findByEmail(String email);
}
