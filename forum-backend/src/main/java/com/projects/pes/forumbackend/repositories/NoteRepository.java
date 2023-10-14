package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.NoteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NoteRepository extends CrudRepository<NoteEntity, UUID> {
}
