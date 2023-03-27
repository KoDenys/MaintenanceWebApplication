package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.Repair;
import com.epam.kosyi.sto.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitPageCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.SUBMIT_REQUEST_PAGE, false);

        Car car = DAOFactory.getDAOFactory().CarDAO().getCar(Integer.parseInt(request.getParameter("carId")));
        User owner = car.getUser();
        Repair repair = DAOFactory.getDAOFactory().getRepairDAO().getRepair(Integer.parseInt(request.getParameter("repairId")));
        repair.setRepairedCar(car);
        User worker = DAOFactory.getDAOFactory().getUserDAO().getUser(repair.getWorkerId());

        request.setAttribute("repair", repair);
        request.setAttribute("owner", owner);
        request.setAttribute("worker", worker);
        request.setAttribute("repair", repair);


        return path;
    }
}
