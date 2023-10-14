package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
