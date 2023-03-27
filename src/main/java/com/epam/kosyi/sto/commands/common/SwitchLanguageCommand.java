package com.epam.kosyi.sto.commands.common;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class SwitchLanguageCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("locale", request.getParameter("locale"));
        Config.set(request.getSession(), "javax.servlet.jsp.jstl.fmt.locale", request.getParameter("locale"));

        if (request.getSession().getAttribute("currentPage") == null){
            return new Path(Path.PAGE_LOGIN, true);
        }

        return (Path) request.getSession().getAttribute("currentPage");
    }
}
