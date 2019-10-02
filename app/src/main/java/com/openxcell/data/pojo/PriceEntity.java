package com.openxcell.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceEntity {

    @Expose
    @SerializedName("monthlyCharge")
    private int monthlyCharge;
    @Expose
    @SerializedName("annualCharge")
    private int annualCharge;

    public int getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setMonthlyCharge(int monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }

    public int getAnnualCharge() {
        return annualCharge;
    }

    public void setAnnualCharge(int annualCharge) {
        this.annualCharge = annualCharge;
    }
}
