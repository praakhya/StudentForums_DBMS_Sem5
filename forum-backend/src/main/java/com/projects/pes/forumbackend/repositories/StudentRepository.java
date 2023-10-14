package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<StudentEntity, UUID> {
}
