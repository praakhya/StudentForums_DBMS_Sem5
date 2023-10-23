package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.entities.StudentEntity;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.StudentMapper;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.pojo.Student;
import com.projects.pes.forumbackend.repositories.StudentRepository;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    public Iterable<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(e -> studentList.add(studentMapper.convert(e)));
        return studentList;
    }
    public Student getStudent(String username) {
        return studentMapper.convert(studentRepository
                .findByUsername(username).orElseThrow(() -> {throw new UserDoesntExist(username);}));
    }
    public Student save(Student student) {
        return studentMapper.convert(studentRepository.save(studentMapper.convert(student)));
    }
    public Student delete(String username) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByUsername(username);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity entity = optionalStudentEntity.get();
            studentRepository.delete(entity);
            return studentMapper.convert(entity);
        }
        throw new UserDoesntExist(username);
    }
    public ProfileImage updateProfileImage(String username,
                                           byte[] bytes,
                                           String mimeType) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByUsername(username);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity entity = optionalStudentEntity.get();
            PictureEntity picture = new PictureEntity(bytes, mimeType);
            entity.setPicture(picture);
            entity = studentRepository.save(entity);
            studentMapper.convert(entity);
            return new ProfileImage(username,
                    Constants.Paths.FACULTY_PATH+Constants.Paths.IMAGE_UPLOAD_PATH.replace("{username}", username),
                    entity.getPicture().getMimeType(),
                    null);
        }
        throw new UserDoesntExist(username);
    }
    public ProfileImage getImage(String username) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByUsername(username);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity entity = optionalStudentEntity.get();
            return new ProfileImage(username,
                    Constants.Paths.IMAGE_UPLOAD_PATH.replace("{username}", username),
                    entity.getPicture().getMimeType(),
                    entity.getPicture().getImageData());
        }
        throw new UserDoesntExist(username);
    }
}
