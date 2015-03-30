package ru.javawebinar.topjava.web.user;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-app.xml"})
public class AdminUserRestControllerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    //    private static ConfigurableApplicationContext applicationContext;
    @Autowired
    private AdminUserRestController controller;

//    @BeforeClass
//    public static void beforeClass() throws Exception {
//        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
//        System.out.println("\n" + Arrays.toString(applicationContext.getBeanDefinitionNames()) + "\n");
//        controller = applicationContext.getBean(AdminUserRestController.class);
//    }
//
//
//    @AfterClass
//    public static void afterClass() throws Exception {
//        applicationContext.close();
//    }

    @Test
    public void testCreate() throws Exception {
        controller.create(new User(null, "Name", "email@ya.ru", "password", true, Role.USER));
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(7);
    }


    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=0");
        controller.delete(0);
    }
}