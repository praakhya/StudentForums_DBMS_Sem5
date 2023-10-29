package com.projects.pes.forumbackend.pojo;

import java.util.UUID;

public record CreateForum(
        UUID adminId,
        String name
) {
}
