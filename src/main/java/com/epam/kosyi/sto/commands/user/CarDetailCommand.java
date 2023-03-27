package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.entities.Repair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CarDetailCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Path path = null;

        Car car = DAOFactory.getDAOFactory().CarDAO().getCar(Integer.parseInt(request.getParameter("carId")));
        List<Repair> repairs = DAOFactory.getDAOFactory().getRepairDAO().getRequests(car);

        if (car != null){
            path = new Path(Path.REPAIRS_MENU, false);
            session.setAttribute("repairlist", DAOFactory.getDAOFactory().getRepairDAO().getRequests(car));
            session.setAttribute("car", car);
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
