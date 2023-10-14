package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<PostEntity, UUID> {
}
