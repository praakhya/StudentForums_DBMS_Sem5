package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.ResourceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResourceRepository extends CrudRepository<ResourceEntity, UUID> {
}
