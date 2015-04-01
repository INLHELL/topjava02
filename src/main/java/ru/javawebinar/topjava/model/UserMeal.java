package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * Created by vladislav.fedotov on 06.03.2015.
 */
public class UserMeal extends BaseEntity {
    private LocalDateTime dateTime;
    private int calories;
    private String description;
    private User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime date, String description, int calories) {
        super(id);
        this.dateTime = date;
        this.calories = calories;
        this.description = description;
    }

    public UserMeal(UserMeal other) {
        this(other.id, other.dateTime, other.description, other.calories);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
