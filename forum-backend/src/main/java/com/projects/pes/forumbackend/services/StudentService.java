package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.ForumMapper;
import com.projects.pes.forumbackend.mappers.StudentMapper;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.repositories.StudentRepository;
import com.projects.pes.forumbackend.utils.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Iterable<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(e -> studentList.add(studentMapper.convert(e)));
        return studentList;
    }
    public Student getStudent(String username) {
        return studentMapper.convert(studentRepository
                .findByUsername(username).orElseThrow(() -> {throw new UserDoesntExist(username);}));
    }
    public Student updateStudent(Student student) {
        StudentEntity studentEntity = studentRepository.findById(student.getId()).orElseThrow(() -> new UserDoesntExist(student.getId().toString()));
        if (student.getEmail()!=null && !studentEntity.getEmail().equals(student.getEmail())) {
            studentEntity.setEmail(student.getEmail());
        }
        if (student.getName()!=null && !studentEntity.getName().equals(student.getName())) {
            studentEntity.setName(student.getName());
        }
        if (studentEntity.getContact() == null || (student.getContact()!=null && !studentEntity.getContact().equals(student.getContact()))) {
            System.out.println("In if: " + studentEntity.getContact()+" "+student.getContact());
            studentEntity.setContact(student.getContact());
        }
        studentEntity.setMemberships(student.getMemberships());
        studentEntity.setPublications(student.getPublications());
        studentEntity.setSkills(student.getSkills());
        studentEntity = studentRepository.save(studentEntity);
        return studentMapper.convert(studentEntity);
    }
    @Transactional
    public Student save(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentMapper.convert(studentRepository.save(studentMapper.convert(student)));
    }
    @Transactional
    public Student delete(String username) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByUsername(username);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity entity = optionalStudentEntity.get();
            Student student = studentMapper.convert(entity);
            studentRepository.delete(entity);
            return student;
        }
        throw new UserDoesntExist(username);
    }
}
