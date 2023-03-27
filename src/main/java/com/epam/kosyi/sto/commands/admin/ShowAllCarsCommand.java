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

public class ShowAllCarsCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.ADMIN_CAR_PAGE, false);
        List <Car> cars = DAOFactory.getDAOFactory().CarDAO().getAllCars();
        request.getSession().setAttribute("cars", cars);
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
