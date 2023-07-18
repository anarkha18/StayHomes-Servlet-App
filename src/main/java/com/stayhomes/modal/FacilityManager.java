package com.stayhome.modal;

public class FacilityManager {
    private int id;
    private String name;
    private String designation;
    private String phone;
    private String responsibilities;

    public FacilityManager(int id, String name, String designation, String phone, String responsibilities) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.phone = phone;
        this.responsibilities = responsibilities;
    }

    public FacilityManager() {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
