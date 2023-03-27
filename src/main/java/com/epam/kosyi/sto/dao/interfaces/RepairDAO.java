package com.epam.kosyi.sto.dao.interfaces;

import com.epam.kosyi.sto.entities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RepairDAO {

    Repair getRepair(int id);

    List<Repair> getAllRepairs();

    List<Repair> getRequests(Car car);

    boolean makePayment(Repair repair);

    RepairState getRepairState(int id);

    RepairType getRepairType(int id);

    boolean createRepair(Repair repair);

    boolean editRepair(Repair repair);

    boolean removeRepair(User user, Repair repair);

    List<RepairType> getListRepairType(Car car);

    List<RepairState> getListRepairState(int id);

    Map<Integer, PriceList> getMapPriseList(Car car);

    PriceList getPriceByRepairId(int repairId, Car car);

    boolean createFeedback(Feedback feedback);

}
