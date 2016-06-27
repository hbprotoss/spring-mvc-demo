package io.hbprotoss.web.model;

import io.hbprotoss.web.validator.annotation.CheckDate;

/**
 * Created by hbprotoss on 9/26/15.
 */
public class UserModel {
    private int id;
    private String name;
    private int age;
    private String description;
    @CheckDate
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
