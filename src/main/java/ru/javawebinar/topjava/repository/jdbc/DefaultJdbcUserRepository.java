package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.api.UserRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by vladislav.fedotov on 17.03.2015.
 */
@Repository
public class DefaultJdbcUserRepository implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(DefaultJdbcUserRepository.class);
    private static final RowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    //@Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultJdbcUserRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM USERS WHERE id=?", id) != 0;
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registered", user.getRegistered())
                .addValue("enabled", user.isEnabled());
//                .addValue("roles", user.getRoles());

        if (user.isNew()) {
            // Could be replace with ROW_MAPPER or not???
            Number newId = simpleJdbcInsert.executeAndReturnKey(map);
            user.setId(newId.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE USERS SET name=:name, email=:email, password=:password, " +
                    "registered=:registered, enabled=:enabled WHERE id=:id", map);
        }
        return user;
    }

    @Override
    public User get(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, email, password, registered, enabled FROM USERS WHERE id=?",
                ROW_MAPPER, id);
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT id, name, email, password, registered, enabled FROM USERS WHERE " +
                        "email=?",
                ROW_MAPPER, email);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT id, name, email, password, registered, enabled FROM USERS ORDER BY name," +
                " email", ROW_MAPPER);
    }
}
