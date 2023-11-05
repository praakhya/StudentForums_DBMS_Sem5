package com.projects.pes.forumbackend.endpoints;

import com.projects.pes.forumbackend.entities.UserNotificationEntity;
import com.projects.pes.forumbackend.repositories.UserNotificationRepository;
import com.projects.pes.forumbackend.services.FacultyService;
import com.projects.pes.forumbackend.services.UserService;
import com.projects.pes.forumbackend.utils.Constants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(Constants.Paths.DASHBOARD_PATH)
public class DashboardEndpoint {
    @Autowired //automatically finds beans and injects it.
    private FacultyService facultyService;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @RequestMapping(value = Constants.Paths.POSTS_COUNT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postCount(@PathVariable("id") UUID id) {
        String query = String.format("select getPostCount(\"%s\")",id.toString());
        List results = entityManager.createNativeQuery(query).getResultList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", Long.parseLong(results.get(0).toString()));
        return ResponseEntity.ok(map);
    }
    @RequestMapping(value = Constants.Paths.NOTIFICATIONS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNotifications(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userNotificationRepository.findByUserId(id));
    }

    @RequestMapping(value = Constants.Paths.NOTIFICATIONS, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteNotifications(@PathVariable("id") UUID id) {
        Iterable<UserNotificationEntity> userNotificationEntities = userNotificationRepository.findByUserId(id);
        userNotificationRepository.deleteAll(userNotificationEntities);
        return ResponseEntity.ok("All notifications deleted");
    }

}
