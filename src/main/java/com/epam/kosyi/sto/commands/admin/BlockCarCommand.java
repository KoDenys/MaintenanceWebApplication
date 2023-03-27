package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BlockCarCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.ADMIN_CAR_PAGE, true);
        Car car = DAOFactory.getDAOFactory().CarDAO().getCar(Integer.parseInt(request.getParameter("carId")));


        String state = request.getParameter("state");
        if(state.equals("true")){
            System.out.println("1");
            DAOFactory.getDAOFactory().CarDAO().setActiveForCar(car,true);
        }
        if(state.equals("false")){
            System.out.println("2");
            DAOFactory.getDAOFactory().CarDAO().setActiveForCar(car,false);
        }
        System.out.println("Car blocked= " + car.getBlocked());


        User user = DAOFactory.getDAOFactory().getUserDAO().getUser(Integer.parseInt(request.getParameter("userId")));
        request.getSession().setAttribute("cars", user.getCars());
        request.getSession().setAttribute("carUser", user);
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
