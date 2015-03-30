package ru.javawebinar.topjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.api.UserMealRepository;
import ru.javawebinar.topjava.service.api.UserMealService;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by vladislav.fedotov on 09.03.2015.
 */
@Service
public class DefaultUserMealService implements UserMealService {
    @Autowired
    private UserMealRepository repository;


    @Override
    public List<UserMeal> getAll(int userId) {
        // userId will be available
        return repository.getAll(userId);
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id, userId), id);
    }

    @Override
    public UserMeal save(UserMeal meal, int userId) {
        // newly created
        return repository.save(meal, userId);
    }

    @Override
    public void update(UserMeal meal, int userId) throws NotFoundException {
        ExceptionUtil.check(repository.save(meal, userId), meal.getId());
    }

    @Override
    public void deleteAll(int userId) {
        // userId will be available
        repository.deleteAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId) throws NotFoundException {
        // ??? what for plus(1, ChronoUnit.DAYS), why no ExceptionUtil.check
        return ExceptionUtil.check(repository.getBetween(start, end.plus(1, ChronoUnit.DAYS), userId), userId);
    }
}
