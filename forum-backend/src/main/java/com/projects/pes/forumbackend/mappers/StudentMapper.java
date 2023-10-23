package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {
    public StudentEntity convert(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setRollNo(student.rollNo());
        studentEntity.setDepartment(student.department());
        studentEntity.setClassID(student.classID());
        studentEntity.setForumID(student.forumID());
        studentEntity.setMemberships(student.memberships());
        studentEntity.setPublications(student.publications());
        studentEntity.setSkills(student.skills());
        return studentEntity;
    }
    public Student convert(StudentEntity studentEntity) {
        return new Student(
                studentEntity.getRollNo(),
                studentEntity.getDepartment(),
                studentEntity.getClassID(),
                studentEntity.getForumID(),
                studentEntity.getMemberships(),
                studentEntity.getPublications(),
                studentEntity.getSkills()
        );
    }

    public List<Student> convert(List<StudentEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }

}
