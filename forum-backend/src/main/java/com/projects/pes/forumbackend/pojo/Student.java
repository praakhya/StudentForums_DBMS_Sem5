package com.projects.pes.forumbackend.pojo;

import java.util.List;
import java.util.UUID;

public record Student(
         String rollNo,
         String department,
         UUID classID,
         UUID forumID,
         List<String>memberships,
         List<String> publications,
         List<String> skills
) {
}
