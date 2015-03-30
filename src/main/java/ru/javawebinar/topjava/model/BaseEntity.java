package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.LoggerWrapper;

/**
 * Created by vladislav.fedotov on 06.03.2015.
 */
public class BaseEntity {
    public static final int START_SEQ = 100_000;
    private static final LoggerWrapper LOG = LoggerWrapper.get(BaseEntity.class);
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (id == null || that.id == null) {
            throw LOG.getIllegalStateException("Equals '" + this + "' and '" + that + "' with null id");
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 0;
        } else {
            return id;
        }
    }
}
