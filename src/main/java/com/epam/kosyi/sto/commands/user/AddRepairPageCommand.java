package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.dao.mysqldao.RepairMySqlDAO;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.RepairType;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddRepairPageCommand implements Command {


    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Path path = null;

        Car car = DAOFactory.getDAOFactory().CarDAO().getCar(Integer.parseInt(request.getParameter("carId")));
        List<RepairType> list = DAOFactory.getDAOFactory().getRepairDAO().getListRepairType(car);
        if (car != null){
            path = new Path(Path.ADD_REQUEST_PAGE, false);
            session.setAttribute("repairtypelist",list);
            session.setAttribute("car", car);
        }

        session.setAttribute("currentPage", path);


        return path;
    }

}
