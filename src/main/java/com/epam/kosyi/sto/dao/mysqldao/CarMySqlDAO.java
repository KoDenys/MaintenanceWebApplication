package com.epam.kosyi.sto.dao.mysqldao;

import com.epam.kosyi.sto.dao.factory.Connector;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.Repair;
import com.epam.kosyi.sto.entities.RepairType;
import com.epam.kosyi.sto.entities.User;
import com.epam.kosyi.sto.dao.interfaces.CarDAO;
import com.epam.kosyi.sto.sql.CarSQL;
import com.epam.kosyi.sto.sql.UserSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;;

public class CarMySqlDAO implements CarDAO {
    private static volatile CarMySqlDAO CarMySqlDAO;

    public static CarMySqlDAO getInstance() {
        CarMySqlDAO localInstance = CarMySqlDAO;
        if (localInstance == null) {
            synchronized (CarMySqlDAO.class) {
                localInstance = CarMySqlDAO;
                if (localInstance == null) {
                    CarMySqlDAO = localInstance = new CarMySqlDAO();
                }
            }
        }
        return localInstance;
    }

    private CarMySqlDAO() {
    }

    @Override
    public List<Car> getCarsForUser(User user) {
        List<Car> carList = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CarSQL.GET_CAR_FOR_USER)) {
            preparedStatement.setInt(1, user.getUserId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = mapCar(resultSet);
                    car.setRepairs(DAOFactory.getDAOFactory().getRepairDAO().getRequests(car));
                    carList.add(car);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return carList;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CarSQL.GET_ALL_CARS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = mapCar(resultSet);
                    car.setRepairs(DAOFactory.getDAOFactory().getRepairDAO().getRequests(car));
                    car.setUser(DAOFactory.getDAOFactory().getUserDAO().getUser(car.getUserId()));
                    carList.add(car);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return carList;
    }

    @Override
    public boolean setActiveForCar(Car car, boolean state) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CarSQL.SET_ACTIVE_FOR_CAR)) {
            preparedStatement.setBoolean(1, state);
            preparedStatement.setInt(2, car.getCarId());

            if (preparedStatement.executeUpdate() > 0){
                car.setBlocked(state);
                result = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean removeCar(Car car, User user) {
       boolean result = false;
       if(user.getUserType().getUserType().equals("admin"))result=true;
       else {
           List<Repair> list = DAOFactory.getDAOFactory().getRepairDAO().getRequests(car);
           if (list == null) result = true;
           else if (list.isEmpty()) result = true;
           else {
               String state;
               for (int i = 0; i < list.size(); i++) {
                   state = list.get(i).getRepairState().getStateValueEn();
                   System.out.println("repair State is " + state);
                   if (state.equals("Created") || state.equals("Finished")) {
                       result = true;
                   } else {
                       result = false;
                       return result;
                   }
               }
           }
       }
       if(result) {
           result = false;

           try (Connection connection = Connector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CarSQL.REMOVE_CAR)) {
               preparedStatement.setInt(1, car.getCarId());
               if (preparedStatement.executeUpdate() > 0) {
                   result = true;
               }
           } catch (SQLException exception) {
               exception.printStackTrace();
           }
       }
        return result;
    }

    @Override
    public Car getCar(int id) {
        Car car = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CarSQL.GET_CAR)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    car = mapCar(resultSet);
                    car.setUser(DAOFactory.getDAOFactory().getUserDAO().getUser(car.getUserId()));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return car;
    }


    @Override
    public boolean addMileage(int mileage, int idCar) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CarSQL.ADD_MILEAGE)) {
            preparedStatement.setInt(1, mileage);
            preparedStatement.setInt(2, idCar);

            if (preparedStatement.executeUpdate() > 0){
                result = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }


    @Override
    public boolean addCar(Car car) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CarSQL.ADD_CAR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getCarNumber());
            preparedStatement.setString(2, car.getCarName());
            preparedStatement.setString(3, car.getCarModel());
            preparedStatement.setString(4, car.getCarColor());
            preparedStatement.setInt(5, car.getCarYear());
            preparedStatement.setInt(6, car.getCarMileage());
            preparedStatement.setInt(7, car.getUserId());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        car.setUserId(resultSet.getInt(1));
                        result = true;
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }


    private Car mapCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();

        car.setCarId(resultSet.getInt(CarSQL.CAR_ID));
        car.setCarNumber(resultSet.getString(CarSQL.CAR_NUMBER));
        car.setCarName(resultSet.getString(CarSQL.CAR_NAME));
        car.setCarModel(resultSet.getString(CarSQL.CAR_MODEL));
        car.setCarColor(resultSet.getString(CarSQL.CAR_COLOR));
        car.setCarYear(resultSet.getInt(CarSQL.CAR_YEAR));
        car.setCarMileage(resultSet.getInt(CarSQL.MILEAGE));
        car.setBlocked(resultSet.getBoolean(CarSQL.BLOCKED));
        car.setUserId(resultSet.getInt(CarSQL.USER));

        return car;
    }
}
