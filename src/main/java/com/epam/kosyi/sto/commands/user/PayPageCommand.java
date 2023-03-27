package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Repair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayPageCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.PAY_PAGE, true);
        Repair repair = DAOFactory.getDAOFactory().getRepairDAO().getRepair(Integer.parseInt(request.getParameter("repairId")));
        request.getSession().setAttribute("repair", repair);
        request.getSession().setAttribute("currentPage", path);
        return path;
    }
}
