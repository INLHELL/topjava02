package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.user.AdminUserRestController;

import java.util.Arrays;

/**
 * Created by vladislav.fedotov on 09.03.2015.
 */
public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("\n" + Arrays.toString(context.getBeanDefinitionNames()) + "\n");
            AdminUserRestController adminController = context.getBean(AdminUserRestController.class);
            adminController.delete(7);
            adminController.getByEmail("dummy");
        }

    }
}
