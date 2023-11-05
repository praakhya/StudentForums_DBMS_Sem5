package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SectionRepository extends CrudRepository<SectionEntity, UUID> {
    @Query("select s from SectionEntity s where s.classRep.username = :username or s.classTeacher.username = :username")
    Optional<SectionEntity> findByUsername(String username);

    @Query("select s from SectionEntity s where s IN (select f.section from ForumEntity f where f in (select u.forums from UserEntity u where u.username = :username))")
    Optional<SectionEntity> findByMembername(String username);
}
