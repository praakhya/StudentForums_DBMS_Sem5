package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.ForumEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ForumRepository extends CrudRepository<ForumEntity, UUID> {
}
