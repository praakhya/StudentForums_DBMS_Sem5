package com.projects.pes.forumbackend.services;

import com.projects.pes.forumbackend.entities.FacultyEntity;
import com.projects.pes.forumbackend.entities.ForumEntity;
import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.exceptions.EntityDoesntExist;
import com.projects.pes.forumbackend.exceptions.UserDoesntExist;
import com.projects.pes.forumbackend.mappers.FacultyMapper;
import com.projects.pes.forumbackend.mappers.SectionMapper;
import com.projects.pes.forumbackend.pojo.Faculty;
import com.projects.pes.forumbackend.pojo.Section;
import com.projects.pes.forumbackend.repositories.FacultyRepository;
import com.projects.pes.forumbackend.repositories.ForumRepository;
import com.projects.pes.forumbackend.repositories.SectionRepository;
import com.projects.pes.forumbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Section getSection(UUID id) {

        return sectionMapper.convert(sectionRepository.findById(id).orElseThrow(() -> new EntityDoesntExist(id.toString(), "section")));
    }
    @Transactional
    public Section getSectionForUser(String username) {

        return sectionMapper.convert(sectionRepository.findByMembername(username).orElseThrow(() -> new EntityDoesntExist(username, "section")));
    }
    @Transactional
    public Section createSection(Section section) {

        SectionEntity sectionEntity = sectionRepository.save(sectionMapper.convert(section));

        return sectionMapper.convert(sectionEntity);
    }
    @Transactional
    public Section deleteSection(UUID id) {

        SectionEntity sectionEntity
                = sectionRepository.findById(id).orElseThrow(() -> new EntityDoesntExist(id.toString(), "section"));
        Optional<ForumEntity> optionalForumEntity = forumRepository.findBySection(sectionEntity);
        optionalForumEntity.ifPresent(forumEntity -> {
            forumEntity.getUsers().forEach( u -> {
                if (u.getForums() != null) {
                    u.getForums().remove(forumEntity);
                }
                userRepository.save(u);
            });
            forumRepository.delete(forumEntity);
        });
        Section section = sectionMapper.convert(sectionEntity);
        sectionRepository.delete(sectionEntity);
        return section;
    }
}
