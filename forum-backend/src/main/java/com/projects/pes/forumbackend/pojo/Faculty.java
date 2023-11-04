package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class Faculty extends User {
        public Faculty() {
            super();
            setRole(UserRole.FACULTY);
        }
        public Faculty (
                UUID id,
                String username,
                String email,
                String name,
                String password,
                String imgUrl,
                String contact,
                Set<Forum> forums,
                Section section,
                String jobTitle,
                String department,
                List<String>domains,
                List<String> publications
        ) {
            super(id,username,email,name,password,imgUrl,section,contact,forums,UserRole.FACULTY);
            this.jobTitle = jobTitle;
            this.department = department;
            this.domains = domains;
            this.publications = publications;
        }
        private String jobTitle;
        private String department;
        private List<String> domains;
        private List<String> publications;
    }
