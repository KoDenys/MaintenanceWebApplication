package com.epam.kosyi.sto;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.commands.CommandContainer;
import org.apache.log4j.Logger;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Command command = CommandContainer.get(request.getParameter("command"));
        System.out.println(command);
        Path page = command.execute(request, response);

        boolean isRedirect = page.isRedirect();

        if (isRedirect) {
            redirect(page, request, response);
        } else {
            forward(page, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void redirect(Path page, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = page.getPageUrl();
        response.sendRedirect(request.getContextPath() + url);
    }

    private void forward(Path page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = page.getPageUrl();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}