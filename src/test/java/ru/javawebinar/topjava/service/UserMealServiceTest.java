package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.api.UserMealService;
import ru.javawebinar.topjava.util.DatabasePopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by vladislav.fedotov on 02.04.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    private static int USER_ID = START_SEQ + 1;
    private static int ADMIN_ID = START_SEQ;
    private static int INVALID_USER_ID = 1;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private UserMealService service;

    @Autowired
    private DatabasePopulator populator;

    @Before
    public void setUp() throws Exception {
        populator.execute();
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2), service.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        service.delete(MEAL_ID, INVALID_USER_ID);
    }

    @Test
    public void testSave() throws Exception {
        final UserMeal created = getCreated();
        service.save(created, USER_ID);
        MATCHER.assertListEquals(Arrays.asList(created, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        final UserMeal userMeal = service.get(MEAL_ID, USER_ID);
        MATCHER.assertEquals(MEAL1, userMeal);
    }

    @Test
    public void testGetNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        service.get(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        final UserMeal updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID, USER_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        final UserMeal userMeal = service.get(MEAL_ID, USER_ID);
        exception.expect(NotFoundException.class);
        service.update(userMeal, INVALID_USER_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL2, MEAL1), service.getBetween(LocalDateTime.of(2015, 1, 6, 8, 0),
                LocalDateTime.of(2015, 1, 6, 14, 0), USER_ID));

    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(USER_ID);
        Assert.assertTrue(service.getAll(USER_ID).isEmpty());
    }
}
