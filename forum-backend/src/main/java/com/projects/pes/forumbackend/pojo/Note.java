package com.projects.pes.forumbackend.pojo;

import java.util.UUID;

public record Note(
         UUID authorId,
         String content,
         String title
) {
}
