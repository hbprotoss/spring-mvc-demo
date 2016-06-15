package io.hbprotoss.web.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hbprotoss on 9/25/15.
 */
public class JsonModel {
    private int id;
    private String name;
    private String testId;

    public JsonModel() {
    }

    public JsonModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
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
}
