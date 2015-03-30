package ru.javawebinar.topjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.api.UserRepository;
import ru.javawebinar.topjava.service.api.UserService;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by vladislav.fedootv on 09.03.2015.
 */
@Service
public class DefaultUserService implements UserService {
    private static final LoggerWrapper LOG = LoggerWrapper.get(DefaultUserService.class);

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return ExceptionUtil.check(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) throws NotFoundException {
        ExceptionUtil.check(repository.save(user), user.getId());
    }
}
