package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.ForumEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ForumRepository extends CrudRepository<ForumEntity, UUID> {
    @Query("select f from ForumEntity f where f.name like :name")
    Optional<ForumEntity> findByName(String name);
}
