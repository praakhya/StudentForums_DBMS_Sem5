package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.entities.SectionEntity;
import com.projects.pes.forumbackend.entities.UserEntity;
import com.projects.pes.forumbackend.pojo.Section;
import com.projects.pes.forumbackend.services.SectionService;
import com.projects.pes.forumbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Constants.Paths.SECTION_PATH)
public class SectionEndpoint {

    @Autowired
    private SectionService sectionService;
    @RequestMapping(value = Constants.Paths.SECTION_ID_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private Optional<Section> getSection(@PathVariable("id") UUID id) {
        return Optional.of(sectionService.getSection(id));
    }
    @RequestMapping(value = Constants.Paths.SECTION_ID_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    private Optional<Section> deleteSection(@PathVariable("id") UUID id) {
        return Optional.of(sectionService.deleteSection(id));
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private Optional<Section> createSection(@RequestBody Section section) {
        return Optional.of(sectionService.createSection(section));
    }
    @RequestMapping(value = Constants.Paths.SECTION_FROM_USERNAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Section> getSection(@PathVariable("username") String username) {
        return Optional.of(sectionService.getSectionForUser(username));
    }
}
