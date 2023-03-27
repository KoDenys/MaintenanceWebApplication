package com.epam.kosyi.sto.entities;

import java.util.List;

public class RepairType {
    private int repairTypeId;
    private String repairTypeNameEn;
    private String repairTypeNameUk;
    private PriceList priceList;

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }






    public int getRepairTypeId() {
        return repairTypeId;
    }

    public void setRepairTypeId(int repairTypeId) {
        this.repairTypeId = repairTypeId;
    }

    public String getRepairTypeNameEn() {
        return repairTypeNameEn;
    }

    public void setRepairTypeNameEn(String repairTypeNameEn) {
        this.repairTypeNameEn = repairTypeNameEn;
    }

    public String getRepairTypeNameUk() {
        return repairTypeNameUk;
    }

    public void setRepairTypeNameUk(String repairTypeNameUk) {
        this.repairTypeNameUk = repairTypeNameUk;
    }


}
