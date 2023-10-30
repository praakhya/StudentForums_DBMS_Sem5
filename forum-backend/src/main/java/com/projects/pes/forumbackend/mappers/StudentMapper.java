package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.pojo.UserRole;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        studentEntity.setPicture(new PictureEntity(null,null, Constants.Paths.DUMMY_PROFILE_PICTURE));
        studentEntity.setContact(student.getContact());
        studentEntity.setRole(UserRole.STUDENT);
        studentEntity.setRollNo(student.getRollNo());
        studentEntity.setDepartment(student.getDepartment());
        studentEntity.setClassId(student.getClassId());
        studentEntity.setMemberships(student.getMemberships());
        studentEntity.setPublications(student.getPublications());
        studentEntity.setSkills(student.getSkills());
        return studentEntity;
    }
    public Student convert(StudentEntity studentEntity) {
        PictureEntity pictureEntity = studentEntity.getPicture();
        byte[] imageData = null;
        String mimeType = null;
        if (pictureEntity != null) {
            imageData = pictureEntity.getImageData();
            mimeType = pictureEntity.getMimeType();
        }
        return new Student(
                studentEntity.getId(),
                studentEntity.getUsername(),
                studentEntity.getEmail(),
                studentEntity.getName(),
                studentEntity.getPassword(),
                studentEntity.getPicture().getUrl(),
                studentEntity.getContact(),
                studentEntity.getForums() == null ? new HashSet<>() : studentEntity.getForums().stream().map(f->new Forum(f.getId(), null, f.getName(), null, null, null)).collect(Collectors.toSet()),
                studentEntity.getRollNo(),
                studentEntity.getDepartment(),
                studentEntity.getClassId(),
                studentEntity.getMemberships(),
                studentEntity.getPublications(),
                studentEntity.getSkills()
        );
    }

    public List<Student> convert(List<StudentEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }

}
