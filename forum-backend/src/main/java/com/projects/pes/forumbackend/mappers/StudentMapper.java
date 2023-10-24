package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentMapper {
    public StudentEntity convert(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUsername(student.getUsername());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setName(student.getName());
        studentEntity.setPassword(student.getPassword());
        studentEntity.setPicture(new PictureEntity(student.getImageData(),student.getMimeType()));
        studentEntity.setContact(student.getContact());
        studentEntity.setForums(student.getForums());
        studentEntity.setRollNo(student.getRollNo());
        studentEntity.setDepartment(student.getDepartment());
        studentEntity.setClassId(student.getClassId());
        studentEntity.setForumId(student.getForumId());
        studentEntity.setMemberships(student.getMemberships());
        studentEntity.setPublications(student.getPublications());
        studentEntity.setSkills(student.getSkills());
        return studentEntity;
    }
    public Student convert(StudentEntity studentEntity) {
        return new Student(
                studentEntity.getId(),
                studentEntity.getUsername(),
                studentEntity.getEmail(),
                studentEntity.getName(),
                studentEntity.getPassword(),
                studentEntity.getPicture().getImageData(),
                studentEntity.getPicture().getMimeType(),
                studentEntity.getContact(),
                studentEntity.getForums(),
                studentEntity.getRollNo(),
                studentEntity.getDepartment(),
                studentEntity.getClassId(),
                studentEntity.getForumId(),
                studentEntity.getMemberships(),
                studentEntity.getPublications(),
                studentEntity.getSkills()
        );
    }

    public List<Student> convert(List<StudentEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }

}
