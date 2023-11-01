package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.ResourceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceRepository extends CrudRepository<ResourceEntity, UUID> {
    @Query("select r from ResourceEntity r where r.ownerName = :username")
    List<ResourceEntity> findByUsername(String username);

}
