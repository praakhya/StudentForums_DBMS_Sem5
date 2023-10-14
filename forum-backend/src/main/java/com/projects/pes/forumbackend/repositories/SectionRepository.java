package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.SectionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SectionRepository extends CrudRepository<SectionEntity, UUID> {
}
