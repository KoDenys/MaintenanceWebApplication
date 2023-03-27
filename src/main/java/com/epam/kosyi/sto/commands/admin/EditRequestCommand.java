package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.PriceList;
import com.epam.kosyi.sto.entities.Repair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRequestCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.SUCCESS_REQUEST_PAGE, true);
        Repair repair = DAOFactory.getDAOFactory().getRepairDAO().getRepair(Integer.parseInt(request.getParameter("repairId")));
        String description = request.getParameter("description");
        int mileage = Integer.parseInt(request.getParameter("currentMileage"));
        System.out.println("Description =" + description);


        if(description!=""){
        repair.setDescription(request.getParameter("description"));
        }
        repair.setRepairSum(Double.parseDouble(request.getParameter("repairSum")));
        repair.setDiscount(Double.parseDouble(request.getParameter("discount")));
        repair.setCount(Integer.parseInt(request.getParameter("count")));
        repair.setWorkerId(Integer.parseInt(request.getParameter("worker")));
        repair.setRepairStateId(Integer.parseInt(request.getParameter("repairState")));
        repair.setCarId(Integer.parseInt(request.getParameter("carId")));
        repair.setRepairTypeId(Integer.parseInt(request.getParameter("repairType")));
        if(repair.getCurrentMileage()!=mileage) {
            repair.setCurrentMileage(mileage);
            DAOFactory.getDAOFactory().CarDAO().addMileage(mileage,repair.getCarId());
        }
        if (!DAOFactory.getDAOFactory().getRepairDAO().editRepair(repair)){

            return new Path(Path.FAILED_PAGE, true);
        }


        return path;
    }
}