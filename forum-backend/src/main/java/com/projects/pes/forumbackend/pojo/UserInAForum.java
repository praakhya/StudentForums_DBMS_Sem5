package com.projects.pes.forumbackend.pojo;

import java.util.UUID;

public record UserInAForum(
        UUID id,
        String username,
        String url
) {
}
