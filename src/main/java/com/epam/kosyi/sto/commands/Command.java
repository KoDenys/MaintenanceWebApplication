package com.epam.kosyi.sto.commands;

import com.epam.kosyi.sto.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
