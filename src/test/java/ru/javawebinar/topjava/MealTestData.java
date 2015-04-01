package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * Created by vladislav.fedotov on 18.03.2015.
 */
public class MealTestData {

    public static final int MEAL_ID = BaseEntity.START_SEQ + 2;

    public static final UserMeal MEAL1 = new UserMeal(MEAL_ID, LocalDateTime.of(2015, 1, 6, 9, 0), "breakfast", 500);
    public static final UserMeal MEAL2 = new UserMeal(MEAL_ID + 1, LocalDateTime.of(2015, 1, 6, 13, 0), "dinner",
            1000);
    public static final UserMeal MEAL3 = new UserMeal(MEAL_ID + 2, LocalDateTime.of(2015, 1, 7, 0, 0), "supper",
            600);
    public static final UserMeal MEAL4 = new UserMeal(MEAL_ID + 3, LocalDateTime.of(2015, 1, 7, 13, 0), "dinner",
            1300);
    public static final UserMeal ADMIN_MEAL = new UserMeal(MEAL_ID + 4, LocalDateTime.of(2015, 1, 6, 14, 0),
            "admin_meal", 2000);
    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher(new Function<UserMeal, String>() {
        @Override
        public String apply(UserMeal userMeal) {
            return userMeal.toString();
        }
    });

    public static UserMeal getUpdated() {
        UserMeal updated = new UserMeal(MEAL1);
        updated.setDescription("Updated breakfast");
        return updated;
    }

    public static UserMeal getCreated() {
        UserMeal created = new UserMeal(null, LocalDateTime.of(2015, 1, 8, 18, 0), "created", 300);
        return created;
    }

}
