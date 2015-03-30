package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.api.UserService;
import ru.javawebinar.topjava.util.DatabasePopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vladislav.fedootv on 19.03.2015.
 */

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private UserService service;

    @Autowired
    private DatabasePopulator databasePopulator;

    @Before
    public void setUp() throws Exception {
        databasePopulator.execute();
    }

    @Test
    public void testSave() {
        UserTestData.TestUser testUser = new UserTestData.TestUser("New", "new@gmail.com", "newPass", Role.USER);
        User createdUser = service.save(testUser.asUser());
        testUser.setId(createdUser.getId());
        UserTestData.MATCHER.assertListEquals(Arrays.asList(UserTestData.ADMIN, testUser, UserTestData.USER),
                service.getAll());
    }

    @Test
    public void testDuplicateMailSave() {
        exception.expect(DataAccessException.class);
        service.save(new UserTestData.TestUser("Duplicate", "user@yandex.ru", "newPass", Role.USER).asUser());
    }

    @Test
    public void testDelete() {
        service.delete(BaseEntity.START_SEQ);
        UserTestData.MATCHER.assertListEquals(Collections.singletonList(UserTestData.USER), service.getAll());
    }

    @Test
    public void testNotFoundDelete() {
        exception.expect(NotFoundException.class);
        service.delete(-1);
    }

    @Test
    public void testGet() {
        final User user = service.get(BaseEntity.START_SEQ);
        UserTestData.MATCHER.assertEquals(user, service.get(BaseEntity.START_SEQ));
    }

    @Test
    public void testGetByEmail() {
        final User user = service.getByEmail(UserTestData.ADMIN.getEmail());
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, user);
    }

    @Test
    public void testGetAll() {
        final List<User> userList = service.getAll();
        UserTestData.MATCHER.assertListEquals(Arrays.asList(UserTestData.ADMIN, UserTestData.USER), userList);
    }

    @Test
    public void testUpdate() {
        UserTestData.TestUser testUser = new UserTestData.TestUser(UserTestData.ADMIN);
        testUser.setName("SomeName");
        service.update(testUser);
        UserTestData.MATCHER.assertEquals(testUser, service.get(BaseEntity.START_SEQ));

    }


}
