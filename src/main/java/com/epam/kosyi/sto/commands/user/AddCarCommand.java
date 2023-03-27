package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.User;
import com.epam.kosyi.sto.Path;
import org.iban4j.Iban;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

import static com.epam.kosyi.sto.Path.CAR_MENU;

public class AddCarCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Path path = null;

        Car car = new Car();
        car.setCarNumber(request.getParameter("carNumber"));
        car.setCarName(request.getParameter("carName"));
        car.setCarModel(request.getParameter("carModel"));
        car.setCarColor(request.getParameter("carColor"));
        car.setCarYear(Integer.parseInt(request.getParameter("carYear")));
        car.setCarMileage(Integer.parseInt(request.getParameter("carMileage")));
        car.setUserId(user.getUserId());
        car.setUser(user);


        if (DAOFactory.getDAOFactory().CarDAO().addCar(car)){
            session.setAttribute("user", DAOFactory.getDAOFactory().getUserDAO().getUser(user.getLogin(), user.getPassword()));
            path = new Path(CAR_MENU, true);
        }

        session.setAttribute("currentPage", path);

        return path;
    }

}
