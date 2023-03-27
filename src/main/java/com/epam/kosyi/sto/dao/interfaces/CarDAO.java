package com.epam.kosyi.sto.dao.interfaces;

import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface CarDAO {

    List<Car> getCarsForUser(User user);

    List<Car> getAllCars();

    Car getCar(int id);

    boolean setActiveForCar(Car car, boolean state);

    boolean removeCar(Car car, User user);

    boolean addMileage(int mileage, int idCar);

    boolean addCar(Car car);



}
