package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.Repair;
import com.epam.kosyi.sto.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RemoveRequestCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        Path path = (Path) session.getAttribute("currentPage");



        if (user != null) {
            if (DAOFactory.getDAOFactory().getRepairDAO().removeRepair(user,
                    DAOFactory.getDAOFactory().getRepairDAO().getRepair(Integer.parseInt(request.getParameter("repairId"))))) {
                path = new Path(Path.SUCCESS_REQUEST_PAGE, true);
            } else {
                path = new Path(Path.FAILED_PAGE, true);
            }
            request.getSession().setAttribute("currentPage", path);

        }
        return path;
    }
}
