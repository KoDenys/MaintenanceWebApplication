package com.epam.kosyi.sto.entities;

import java.math.BigDecimal;

public class PriceList {
    private int priceListId;
    private double price;
    private int guarantee;
    private int repairType;

    public int getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRepairType() {
        return repairType;
    }

    public void setRepairType(int repairType) {
        this.repairType = repairType;
    }


    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }


}
