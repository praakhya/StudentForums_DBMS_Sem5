package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    @Query("select p from PostEntity p where p.posterId = :posterId")
    Optional<UserEntity> findByPosterId(String posterId);
    @Query("select u from UserEntity u where u.username = :username")
    Optional<UserEntity> findByUsername(String username);

}
