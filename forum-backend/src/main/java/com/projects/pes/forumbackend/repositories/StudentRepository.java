package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends CrudRepository<StudentEntity, UUID> {
    @Query("select s from StudentEntity s, UserEntity ue where ue.id = s.id and ue.username = :username")
    Optional<StudentEntity> findByUsername(String username);
    @Query("SELECT s FROM StudentEntity s WHERE s.email = :email")
    Optional<StudentEntity> findByEmail(String email);
}
