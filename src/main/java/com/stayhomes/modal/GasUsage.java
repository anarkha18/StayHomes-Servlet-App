package com.stayhome.modal;

import java.util.Date;

public class GasUsage {
    private int id;
    private flats flatId;
    private Date usageMonth;
    private double unitsConsumed;
    private GasUnitRate rateId;
    private double amount;

    public GasUsage() {
    }

    public GasUsage(int id, flats flatId, Date usageMonth, double unitsConsumed, GasUnitRate rateId, double amount) {
        this.id = id;
        this.flatId = flatId;
        this.usageMonth = usageMonth;
        this.unitsConsumed = unitsConsumed;
        this.rateId = rateId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public flats getFlatId() {
        return flatId;
    }

    public void setFlatId(flats flatId) {
        this.flatId = flatId;
    }

    public Date getUsageMonth() {
        return usageMonth;
    }

    public void setUsageMonth(Date usageMonth) {
        this.usageMonth = usageMonth;
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public GasUnitRate getRateId() {
        return rateId;
    }

    public void setRateId(GasUnitRate rateId) {
        this.rateId = rateId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
