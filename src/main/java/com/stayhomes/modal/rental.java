package com.stayhome.modal;

import java.io.Serializable;

public class rental implements Serializable {
    private int id;
    private  flats flat;
    private users tenant;

    public rental() {
    }

    public rental(int id, flats flat, users tenant) {
        this.id = id;
        this.flat = flat;
        this.tenant = tenant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public flats getFlat() {
        return flat;
    }

    public void setFlat(flats flat) {
        this.flat = flat;
    }

    public users getTenant() {
        return tenant;
    }

    public void setTenant(users tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "rental{" +
                "id=" + id +
                ", flat=" + flat +
                ", tenant=" + tenant +
                '}';
    }
}
