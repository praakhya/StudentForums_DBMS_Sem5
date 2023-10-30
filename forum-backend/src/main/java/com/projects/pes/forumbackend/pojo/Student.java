package com.projects.pes.forumbackend.pojo;

import com.projects.pes.forumbackend.entities.ForumEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Data
public class Student extends User {

    public Student() {
        super();
        setRole(UserRole.STUDENT);
    }
    public Student(
         UUID id,
         String username,
         String email,
         String name,
         String password,
         String imgUrl,
         String contact,
         Set<Forum> forums,
         String rollNo,
         String department,
         UUID classId,
         List<String>memberships,
         List<String> publications,
         List<String> skills) {
        super(id,username,email,name,password,imgUrl,contact,forums,UserRole.STUDENT);
        this.rollNo = rollNo;
        this.department = department;
        this.classId = classId;
        this.forumId = forumId;
        this.memberships = memberships;
        this.publications = publications;
        this.skills = skills;

    }
    private String rollNo;
    private String department;
    private UUID classId;
    private UUID forumId;
    private List<String>memberships;
    private List<String> publications;
    private List<String> skills;

}

