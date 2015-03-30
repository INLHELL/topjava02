package ru.javawebinar.topjava.service.api;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vladislav.fedotov on 06.03.2015.
 */
public interface UserMealService {
    List<UserMeal> getAll(int userId);

    UserMeal get(int id, int userId);

    void delete(int id, int userId);

    UserMeal save(UserMeal meal, int userId);

    void update(UserMeal meal, int userId);

    void deleteAll(int userId);

    List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId);
}
