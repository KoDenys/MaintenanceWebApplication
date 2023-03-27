package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.dao.mysqldao.RepairMySqlDAO;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.PriceList;
import com.epam.kosyi.sto.entities.Repair;
import com.epam.kosyi.sto.dao.mysqldao.RepairMySqlDAO;
import com.epam.kosyi.sto.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRepair implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.SUCCESS_REQUEST_PAGE, true);
        Repair repair = new Repair();

        Car car = (Car) request.getSession().getAttribute("car");
//        if (car == null){
//            return new Path(Path.FAILED_PAGE, true);
//        }

        int repairTypeId = Integer.parseInt(request.getParameter("typeRepair"));
        int count = Integer.parseInt(request.getParameter("count"));

       // Car car = DAOFactory.getDAOFactory().CarDAO().getCar(Integer.parseInt(request.getSession().getAttribute("carId")));

        PriceList priceList = DAOFactory.getDAOFactory().getRepairDAO().getPriceByRepairId(repairTypeId, car);
            double sum = priceList.getPrice() * count;

        repair.setRepairSum(sum);
        repair.setDescription("---");
        repair.setDiscount(0);
        repair.setCount(count);
        repair.setWorkerId(58);
        repair.setRepairStateId(1);
        repair.setCarId(car.getCarId());
        repair.setCurrentMileage(car.getCarMileage());
        repair.setRepairTypeId(repairTypeId);
        repair.setPriceListId(777);

        if (!DAOFactory.getDAOFactory().getRepairDAO().createRepair(repair)){

            return new Path(Path.FAILED_PAGE, true);
        }

        return path;
    }
}
