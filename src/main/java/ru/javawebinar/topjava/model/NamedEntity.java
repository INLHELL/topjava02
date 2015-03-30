package ru.javawebinar.topjava.model;

/**
 * Created by vladislav.fedotov on 06.03.2015.
 */
public class NamedEntity extends BaseEntity {
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(String name) {
        this.name = name;
    }

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
