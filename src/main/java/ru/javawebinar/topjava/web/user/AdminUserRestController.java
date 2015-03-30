package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.api.UserService;

import java.util.List;

/**
 * Created by vladislav.fedootv on 06.03.2015.
 */
@Controller
public class AdminUserRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(AdminUserRestController.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        LOG.info("create {}", user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user) {
        LOG.info("save {}", user);
        service.update(user);
    }

    public User getByEmail(String email) {
        LOG.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

}
