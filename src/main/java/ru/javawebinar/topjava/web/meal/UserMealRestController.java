package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.api.UserMealService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vladislav.fedotov on 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public List<UserMeal> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll userId: {}", userId);
        return service.getAll(userId);
    }

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get id: {}, userId: {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete id: {}, userId: {}: {}", id, userId);
        service.delete(id, userId);
    }

    public UserMeal create(UserMeal meal) {
        int userId = LoggedUser.id();
        LOG.info("create meal: {}, userId: {}", meal, userId);
        return service.save(meal, userId);
    }

    public void update(UserMeal meal) {
        int userId = LoggedUser.id();
        LOG.info("save meal: {}, userId: {}", userId, meal);
        service.update(meal, userId);
    }

    public void deleteAll() {
        int userId = LoggedUser.id();
        LOG.info("deleteAll userId: {}", userId);
        service.deleteAll(userId);
    }

    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end) {
        int userId = LoggedUser.id();
        LOG.info("getBetween start: {}, end: {}, userId: {}", start, end, userId);
        return service.getBetween(start, end, userId);
    }


}
