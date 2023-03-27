package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.User;
import com.epam.kosyi.sto.Path;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainMenuCommand implements Command {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Path path = (Path) session.getAttribute("currentPage");
       User user = DAOFactory.getDAOFactory().getUserDAO().getUser(((User) request.getSession().getAttribute("user")).getUserId());



        if (user != null){
            session.setAttribute("user", user);
            if (user.getUserType().getUserType().equals("user")){
                path = new Path(Path.CAR_MENU, false);

            }
            else if (user.getUserType().getUserType().equals("manager")){
                System.out.println("MAIN command manager");
                path = new Path(Path.MANAGER_REQUESTS_PAGE, false);
                session.setAttribute("repairs", DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
            }
            else if (user.getUserType().getUserType().equals("admin")){
                path = new Path(Path.ADMIN_MENU, false);

                session.setAttribute("users", DAOFactory.getDAOFactory().getUserDAO().getAllUsers());
                session.setAttribute("repairs", DAOFactory.getDAOFactory().getRepairDAO().getAllRepairs());
            }
        }
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
