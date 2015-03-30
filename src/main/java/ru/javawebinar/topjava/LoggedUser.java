package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;

import java.util.Collections;
import java.util.Set;

/**
 * Created by vladislav.fedootv on 06.03.2015.
 */
public class LoggedUser {
    private static LoggedUser LOGGED_USER = new LoggedUser();

    protected int id = 0;
    protected Set<Role> roles = Collections.singleton(Role.USER);
    protected boolean enabled = true;

    public static LoggedUser get() {
        return LOGGED_USER;
    }

    public static int id() {
        return get().id;
    }

    public Set<Role> getAuthorities() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
