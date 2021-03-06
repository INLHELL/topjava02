package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.api.UserMealRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vladislav.fedotov on 17.03.2015.
 */
@Repository
public class DefaultJdbcUserMealRepository implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(DefaultJdbcUserMealRepository.class);
    private static final RowMapper<UserMeal> ROW_MAPPER =
            (rs, rowNum) -> {
                UserMeal userMeal = new UserMeal();
                userMeal.setId(rs.getInt("id"));
                userMeal.setDateTime(rs.getTimestamp("date").toLocalDateTime());
                userMeal.setDescription(rs.getString("description"));
                userMeal.setCalories(rs.getInt("calories"));
                return userMeal;
            };


    @Autowired
    @Qualifier("userMealJdbcInsert")
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM MEALS WHERE user_id = ? ORDER BY date DESC", ROW_MAPPER, userId);
    }

    @Override
    public UserMeal get(int id, int userId) {
        final List<UserMeal> userMeals = jdbcTemplate.query("SELECT * FROM MEALS WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return CollectionUtils.isEmpty(userMeals) ? null : DataAccessUtils.requiredSingleResult(userMeals);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId) {
        return jdbcTemplate.query("SELECT * FROM MEALS WHERE " +
                "user_id = ? AND date >= ? AND date < ? ORDER BY date DESC", ROW_MAPPER, userId, Timestamp
                .valueOf(start), Timestamp.valueOf(end));
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM MEALS WHERE id = ? AND user_id = ?", id, userId) != 0;
    }

    @Override
    public void deleteAll(int userId) {
        jdbcTemplate.update("DELETE FROM MEALS WHERE user_id = ?", userId);
    }

    @Override
    public UserMeal save(UserMeal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("date", Timestamp.valueOf(meal.getDateTime()))
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories())
                .addValue("user_id", userId);
        if (meal.isNew()) {
            Number newId = simpleJdbcInsert.executeAndReturnKey(map);
            meal.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("UPDATE MEALS SET calories=:calories, " +
                    "description=:description, date=:date WHERE id=:id AND user_id=:user_id", map) == 0) {
                return null;
            }
        }
        return meal;
    }


}
