package com.stayhome.modal;

public class DailyHelpCategory {
    private int id;
    private String name;

    public DailyHelpCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DailyHelpCategory() {
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
