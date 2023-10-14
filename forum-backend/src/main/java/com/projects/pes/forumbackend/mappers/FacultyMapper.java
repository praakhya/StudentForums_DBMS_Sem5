package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.pojo.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyMapper {

    public FacultyEntity convert(Faculty faculty) {
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setId(faculty.id());
        facultyEntity.setDepartment(faculty.department());
        facultyEntity.setDomains(faculty.domains());
        facultyEntity.setPublications(faculty.publications());
        facultyEntity.setName(faculty.name());
        facultyEntity.setJobTitle(faculty.jobTitle());
        facultyEntity.setContact(faculty.contact());
        facultyEntity.setPassword(faculty.password());
        facultyEntity.setUsername(faculty.username());
        return facultyEntity;
    }
    public Faculty convert(FacultyEntity facultyEntity) {
        return new Faculty(facultyEntity.getId(),
                facultyEntity.getName(),
                facultyEntity.getUsername(),
                facultyEntity.getPassword(),
                facultyEntity.getContact(),
                facultyEntity.getJobTitle(),
                facultyEntity.getDepartment(),
                facultyEntity.getDomains(),
                facultyEntity.getDomains());
    }
    public List<Faculty> convert(List<FacultyEntity> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }
}
