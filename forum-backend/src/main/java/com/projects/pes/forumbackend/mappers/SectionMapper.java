package com.projects.pes.forumbackend.mappers;

import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.pojo.Section;
import com.projects.pes.forumbackend.repositories.FacultyRepository;
import com.projects.pes.forumbackend.repositories.ForumRepository;
import com.projects.pes.forumbackend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionMapper {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ForumRepository forumRepository;

    public Section convert(SectionEntity sectionEntity) {
        if (sectionEntity == null) {
            return null;
        }
        return new Section(sectionEntity.getId(),
                sectionEntity.getClassRep().getUsername(),
                sectionEntity.getClassTeacher().getUsername(),
                sectionEntity.getName());
    }
    public SectionEntity convert(Section section) {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setName(section.name());
        sectionEntity.setClassRep(studentRepository.findByUsername(section.classRepUsername()).orElseThrow(() -> new UserDoesntExist(section.classRepUsername())));
        sectionEntity.setClassTeacher(facultyRepository.findByUsername(section.classTeacherUsername()).orElseThrow(() -> new UserDoesntExist(section.classTeacherUsername())));
        return sectionEntity;
    }
}
