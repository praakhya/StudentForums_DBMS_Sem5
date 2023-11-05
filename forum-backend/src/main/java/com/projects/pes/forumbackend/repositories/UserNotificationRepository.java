package com.projects.pes.forumbackend.repositories;

import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.entities.UserNotificationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserNotificationRepository extends CrudRepository<UserNotificationEntity, UUID> {
    @Query("SELECT un from UserNotificationEntity un where un.userId = :userId order by un.ts DESC")
    Iterable<UserNotificationEntity> findByUserId(UUID userId);

}
