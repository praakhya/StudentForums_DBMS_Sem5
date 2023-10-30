package com.projects.pes.forumbackend.pojo;

import java.util.UUID;

public record CreateUser(
        UUID id,
        String username
) {
}
