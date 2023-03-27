package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BlockUserCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.ADMIN_MENU, true);

        User user = DAOFactory.getDAOFactory().getUserDAO().getUser(Integer.parseInt(request.getParameter("user")));
        List<User> users = (List<User>) request.getSession().getAttribute("users");

        if (DAOFactory.getDAOFactory().getUserDAO().setActiveForUser(user, false)){
            for (int i = 0; i < users.size(); i++){
                if (users.get(i).getUserId() == user.getUserId()){
                    users.set(i, user);
                }
            }
            request.getSession().setAttribute("users", users);
        }

        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
