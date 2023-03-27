package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Repair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRequestsCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ShowRequestsCommand#");
        Path path = new Path(Path.ADMIN_REQUESTS_PAGE, false);
        List<Repair> list = DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs();
        System.out.println("list size = "+ list.size());

        request.getSession().setAttribute("repairs", DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
