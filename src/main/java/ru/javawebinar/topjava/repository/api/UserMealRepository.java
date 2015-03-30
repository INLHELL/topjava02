package ru.javawebinar.topjava.repository.api;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vladislav.fedootv on 06.03.2015.
 */
public interface UserMealRepository {

    // ORDERED DATE, TIME
    List<UserMeal> getAll(int userId);

    // null if not found
    UserMeal get(int id, int userId);

    // UserMeal.user = nu
    boolean delete(int id, int userId);

    // UserMeal.user = null
    UserMeal save(UserMeal meal, int userId);

    void deleteAll(int userId);

    List<UserMeal> getBetween(LocalDateTime start, LocalDateTime plus, int userId);
}
