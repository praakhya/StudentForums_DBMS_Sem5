package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.MultimediaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MultimediaRepository extends CrudRepository<MultimediaEntity, UUID> {
}
