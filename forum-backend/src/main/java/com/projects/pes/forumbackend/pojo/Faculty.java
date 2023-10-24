package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.sql.Blob;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class Faculty extends User {
        public Faculty() {
            super();
        }
        public Faculty (
                UUID id,
                String username,
                String email,
                String name,
                String password,
                byte[] imageData,
                String mimeType,
                String contact,
                Set<ForumEntity> forums,
                String jobTitle,
                String department,
                List<String>domains,
                List<String> publications
        ) {
            super(id,username,email,name,password,imageData,mimeType,contact,forums);
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
