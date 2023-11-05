package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.pojo.CreateForum;
import com.projects.pes.forumbackend.pojo.Forum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ForumRepository extends CrudRepository<ForumEntity, UUID> {
    @Query("select f from ForumEntity f where f.name like :name")
    Optional<ForumEntity> findByName(String name);

    @Query("select f from ForumEntity f where f.section like :section")
    Optional<ForumEntity> findBySection(SectionEntity section);

    @Procedure(procedureName = "notifyUserAddToForum")
    void addSubscribeNotification(String username, String forumId);

    @Procedure(procedureName = "notifyUserDeleteFromForum")
    void addUnsubscribeNotification(String userId, String forumId);
}
