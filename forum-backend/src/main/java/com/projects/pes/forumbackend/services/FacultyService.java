package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.FacultyMapper;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.ProfileImage;
import com.projects.pes.forumbackend.repositories.FacultyRepository;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private FacultyMapper facultyMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<Faculty> getFaculties() {
        List<Faculty> facultyList = new ArrayList<>();
        facultyRepository.findAll().iterator().forEachRemaining(e -> facultyList.add(facultyMapper.convert(e)));
        return facultyList;
    }
    public Faculty getFaculty(String username) {
        return facultyMapper.convert(facultyRepository
                .findByUsername(username).orElseThrow(() -> {throw new UserDoesntExist(username);}));
    }
    public Faculty save(Faculty faculty) {
        faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
        return facultyMapper.convert(facultyRepository.save(facultyMapper.convert(faculty)));
    }
    public Faculty delete(String username) {
        Optional<FacultyEntity> optionalFacultyEntity = facultyRepository.findByUsername(username);
        if (optionalFacultyEntity.isPresent()) {
            FacultyEntity entity = optionalFacultyEntity.get();
            facultyRepository.delete(entity);
            return facultyMapper.convert(entity);
        }
        throw new UserDoesntExist(username);
    }
}
