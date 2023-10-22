package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.FacultyPublicationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FacultyPublicationRepository extends CrudRepository<FacultyPublicationEntity, UUID> {
}
