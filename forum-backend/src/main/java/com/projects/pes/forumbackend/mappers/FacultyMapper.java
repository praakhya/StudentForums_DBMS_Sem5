package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.PictureEntity;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.Forum;
import com.projects.pes.forumbackend.pojo.UserRole;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyMapper {

    public FacultyEntity convert(Faculty faculty) {
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setUsername(faculty.getUsername());
        facultyEntity.setEmail(faculty.getEmail());
        facultyEntity.setName(faculty.getName());
        facultyEntity.setPassword(faculty.getPassword());
        facultyEntity.setPicture(new PictureEntity(null,null, Constants.Paths.DUMMY_PROFILE_PICTURE));
        facultyEntity.setContact(faculty.getContact());
        facultyEntity.setRole(UserRole.FACULTY);
        facultyEntity.setId(faculty.getId());
        facultyEntity.setDepartment(faculty.getDepartment());
        facultyEntity.setDomains(faculty.getDomains());
        facultyEntity.setPublications(faculty.getPublications());
        facultyEntity.setJobTitle(faculty.getJobTitle());
        return facultyEntity;
    }
    public Faculty convert(FacultyEntity facultyEntity) {
        PictureEntity pictureEntity = facultyEntity.getPicture();
        byte[] imageData = null;
        String mimeType = null;
        if (pictureEntity != null) {
            imageData = pictureEntity.getImageData();
            mimeType = pictureEntity.getMimeType();
        }
        return new Faculty(facultyEntity.getId(),
                facultyEntity.getUsername(),
                facultyEntity.getEmail(),
                facultyEntity.getName(),
                facultyEntity.getPassword(),
                facultyEntity.getPicture().getUrl(),
                facultyEntity.getContact(),
                facultyEntity.getForums() == null ? new HashSet<>() : facultyEntity.getForums().stream().map(f->new Forum(f.getId(), null, f.getName(), null, null, null)).collect(Collectors.toSet()),
                facultyEntity.getJobTitle(),
                facultyEntity.getDepartment(),
                facultyEntity.getDomains(),
                facultyEntity.getPublications()

        );
    }
    public List<Faculty> convert(List<FacultyEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }
}
