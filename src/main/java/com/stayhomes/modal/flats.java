package com.stayhome.modal;

import java.io.Serializable;

public class flats implements Serializable {
    private int id;

    private String flatNo;
    private String flatType;




    private users flatOwner;
    public flats() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public users getFlatOwner() {
        return flatOwner;
    }

    public void setFlatOwner(users flatOwner) {
        this.flatOwner = flatOwner;
    }

    public flats(int id, String flatNo, String flatType, users flatOwner) {
        this.id = id;
        this.flatNo = flatNo;
        this.flatType = flatType;
        this.flatOwner = flatOwner;
    }

    @Override
    public String toString() {
        return "flats{" +
                "id=" + id +
                ", flatNo='" + flatNo + '\'' +
                ", flatType='" + flatType + '\'' +
                ", flatOwner=" + flatOwner +
                '}';
    }
}
