package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.function.Function;

/**
 * Created by vladislav.fedootv on 18.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher(new Function<UserMeal, String>() {
        @Override
        public String apply(UserMeal userMeal) {
            return userMeal.toString();
        }
    });

}
