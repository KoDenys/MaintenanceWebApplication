package com.epam.kosyi.sto.commands.common;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.PAGE_LOGIN, true);

        HttpSession session = request.getSession();

        if (session != null)
            session.invalidate();

        return path;
    }
}
