package com.epam.kosyi.sto;

import com.epam.kosyi.sto.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandFilter implements Filter {

    private static final Logger log = Logger.getLogger(CommandFilter.class);

    private static final Map<String, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> outOfControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig){
        log.debug("Filter initialization starts");

        // roles
        accessMap.put("admin", asList(filterConfig.getInitParameter("admin")));
        accessMap.put("user", asList(filterConfig.getInitParameter("client")));
        accessMap.put("manager", asList(filterConfig.getInitParameter("manager")));
        commons = asList(filterConfig.getInitParameter("common"));

        log.trace("Access map --> " + accessMap);

        // commons
        commons = asList(filterConfig.getInitParameter("common"));
        log.trace("Common commands --> " + commons);

        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        log.trace("Out of control commands --> " + outOfControl);

        log.debug("Filter initialization finished");
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty())
            return false;

        if (outOfControl.contains(commandName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        User user = (User) session.getAttribute("user");
        if (user.getUserType() == null)
            return false;

        return accessMap.get(user.getUserType().getUserType()).contains(commandName)
                || commons.contains(commandName);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Filter starts");

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (accessAllowed(servletRequest)) {
            log.debug("Filter finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";
            log.trace("Set the request attribute: errorMessage --> " + errorMessage);
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect(req.getContextPath() + Path.PAGE_LOGIN);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
