package com.epam.kosyi.sto.entities;

import java.sql.Timestamp;

public class Repair {
    private int repairId;
    private double repairSum;
    private String description;
    private Timestamp repairDateTime;
    private double discount;
    private int count;
    private int currentMileage;
    private int workerId;
    private int repairStateId;
    private int priceListId;
    private int repairTypeId;
    private int carId;

    private Car repairedCar;
    private RepairState repairState;
    private RepairType repairType;
    private PriceList priceList;
    private User worker;
    private User owner;

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    private Feedback feedback;

    public Repair() {
    }
    public void setRepairSum(double repairSum) {
        this.repairSum = repairSum;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public double getRepairSum() {
        return repairSum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRepairTypeId() {
        return repairTypeId;
    }

    public void setRepairTypeId(int repairTypeId) {
        this.repairTypeId = repairTypeId;
    }

    public Timestamp getRepairDateTime() {
        return repairDateTime;
    }

    public void setRepairDateTime(Timestamp repairDateTime) {
        this.repairDateTime = repairDateTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getRepairStateId() {
        return repairStateId;
    }

    public void setRepairStateId(int repairStateId) {
        this.repairStateId = repairStateId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public void setRepairState(RepairState repairState) {
        this.repairState = repairState;
    }

    public int getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    public Car getRepairedCar() {
        return repairedCar;
    }

    public void setRepairedCar(Car repairedCar) {
        this.repairedCar = repairedCar;
    }

    public RepairState getRepairState() {
        return repairState;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}

