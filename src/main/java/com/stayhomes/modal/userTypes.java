package com.stayhome.modal;

import java.io.Serializable;

public class userTypes  implements Serializable {
    private int id;
    private String userType;
    public userTypes(int id, String userType) {
        this.id = id;
        this.userType = userType;
    }

    public userTypes() {
    }

    public int getId() {
        return id;
    }



    public String getUserType() {
        return userType;
    }


    // Getters and setters

    public void setId(int id) {
        this.id = id;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "userTypes{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                '}';
    }
}
