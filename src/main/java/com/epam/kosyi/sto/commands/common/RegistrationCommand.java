package com.epam.kosyi.sto.commands.common;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.User;
import com.epam.kosyi.sto.entities.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = new User();
        UserInfo userInfo = new UserInfo();

        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("pass"));

        userInfo.setFirstName(request.getParameter("firstName"));
        userInfo.setLastName(request.getParameter("lastName"));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));

        user.setUserInfo(userInfo);
        user.setUserType(DAOFactory.getDAOFactory().getUserDAO().getUserType(1));
        user.setActive(true);

        Path path = new Path(Path.CAR_MENU, false);

        if (DAOFactory.getDAOFactory().getUserDAO().addUser(user)){
            session.setAttribute("user", user);
            path = new Path(Path.CAR_MENU, false);
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
