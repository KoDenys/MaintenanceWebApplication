package com.epam.kosyi.sto.commands.common;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        Path path = new Path(Path.PAGE_LOGIN, true);

        User user = DAOFactory.getDAOFactory().getUserDAO().getUser(login, password);

        if (user != null){
            session.setAttribute("user", user);
            if (user.getUserType().getUserType().equals("user")){
                path = new Path(Path.CAR_MENU, false);
            }
            else if (user.getUserType().getUserType().equals("manager")){
                path = new Path(Path.MANAGER_REQUESTS_PAGE, false);
                session.setAttribute("repairs", DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
            }
            else if (user.getUserType().getUserType().equals("admin")){
                path = new Path(Path.ADMIN_MENU, false);
                System.out.println(DAOFactory.getDAOFactory().getUserDAO().getAllUsers());
                System.out.println(DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
                session.setAttribute("users", DAOFactory.getDAOFactory().getUserDAO().getAllUsers());
                session.setAttribute("repairs", DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
            }
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
