package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminMenuCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.ADMIN_MENU, false);
        System.out.println("lists");
        System.out.println(DAOFactory.getDAOFactory().getUserDAO().getAllUsers());
        System.out.println(DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());

        request.getSession().setAttribute("users", DAOFactory.getDAOFactory().getUserDAO().getAllUsers());
        request.getSession().setAttribute("requests", DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
