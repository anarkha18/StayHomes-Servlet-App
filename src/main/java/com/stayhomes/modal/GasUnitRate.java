package com.stayhome.modal;

public class GasUnitRate {
    private int id;
    private double rate;

    public GasUnitRate(int id, double rate) {
        this.id = id;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public GasUnitRate() {
    }
}
