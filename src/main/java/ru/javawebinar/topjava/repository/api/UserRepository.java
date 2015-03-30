package ru.javawebinar.topjava.repository.api;

import ru.javawebinar.topjava.model.User;

import java.util.List;

/**
 * Created by vladislav.fedootv on 06.03.2015.
 */
public interface UserRepository {
    User save(User user);

    /**
     * @param id
     * @return false if not found
     */
    boolean delete(int id);

    /**
     * @param id
     * @return null if not found
     */
    User get(int id);

    /**
     * @param email
     * @return null if not found
     */
    User getByEmail(String email);

    List<User> getAll();
}
